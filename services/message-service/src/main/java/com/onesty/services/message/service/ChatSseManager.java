package com.onesty.services.message.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.exception.NoActiveSseSubscriberException;
import com.onesty.services.message.persistence.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatSseManager {

    private Map<String, FluxSink<ServerSentEvent>> subscriptions = new ConcurrentHashMap<>();
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    public Flux<ServerSentEvent> createSse(String userId){
        return Flux.create(fluxSink -> {
            log.info("create subscription for " + userId);

            fluxSink.onCancel( // 4
                    () -> {
                        subscriptions.remove(userId);
                        log.info("subscription " + userId + " was closed");
                    }

            );

            subscriptions.put(userId, fluxSink);

            var messages = chatMessageRepository.findAllByToUserId(userId);
            messages.stream().filter(m -> Objects.equals(m.getStatus(), ChatMessageStatuses.SENT)).forEach(m -> m.setStatus(ChatMessageStatuses.DELIVERED));
            ServerSentEvent<List<ChatMessage>> startChatEvent = ServerSentEvent.builder(messages.stream().map(chatMessageMapper::toDto).toList()).build();
            fluxSink.next(startChatEvent);
            chatMessageRepository.saveAll(messages);
        });
    }

    public void sendSseMessage(ChatMessage message) throws NoActiveSseSubscriberException {
        ServerSentEvent<ChatMessage> event = ServerSentEvent
                .builder(message)
                .build();

        if (!subscriptions.containsKey(message.getToUserId())){
            throw new NoActiveSseSubscriberException(String.format("No active user with id = %s", message.getToUserId()));
        }

        subscriptions.get(message.getToUserId()).next(event);
    }
}
