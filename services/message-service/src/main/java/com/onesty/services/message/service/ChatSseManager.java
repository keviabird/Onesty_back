package com.onesty.services.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.exception.NoActiveSseSubscriberException;
import com.onesty.services.message.persistence.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper objectMapper;

    public Flux<ServerSentEvent> createSse(String userId){
        return Flux.create(fluxSink -> {
            log.info("create subscription for " + userId);
            var rabbitAdmin = new RabbitAdmin(rabbitTemplate);

            fluxSink.onCancel(
                    () -> {
                        subscriptions.remove(userId);
                        rabbitAdmin.removeBinding(BindingBuilder.bind(rabbitAdmin.declareQueue()).to(new TopicExchange("chatMessageSseExchange", false, false)).with(userId));
                        log.info("subscription " + userId + " was closed");
                    }

            );

            subscriptions.put(userId, fluxSink);

            var messages = chatMessageRepository.findAllByToUserId(userId);
            messages.stream().filter(m -> Objects.equals(m.getStatus(), ChatMessageStatuses.SENT)).forEach(m -> m.setStatus(ChatMessageStatuses.DELIVERED));
            ServerSentEvent<List<ChatMessage>> startChatEvent = ServerSentEvent.builder(messages.stream().map(chatMessageMapper::toDto).toList()).build();
            fluxSink.next(startChatEvent);

            rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(new TopicExchange("chatMessageSseExchange", false, false)).with(userId));

            chatMessageRepository.saveAll(messages);
        });
    }

    @RabbitListener(queues = "#{@QueueName.QUEUE_NAME}")
    public void listen(String message1) throws NoActiveSseSubscriberException, JsonProcessingException {
        var message = objectMapper.readValue(message1, ChatMessage.class);
        log.info("got message from: " + message.getFromUserId());
        sendSseMessage(message);
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
