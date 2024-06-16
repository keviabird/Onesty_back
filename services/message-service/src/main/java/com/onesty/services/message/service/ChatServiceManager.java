package com.onesty.services.message.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.api.core.message.MessageService;
import com.onesty.services.message.event.IncommingMessageEvent;
import com.onesty.services.message.persistence.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceManager implements MessageService {

    private final ChatSseManager chatSseManager;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void sendMessage(ChatMessage chatMessage) {
        var chatMessageEntity = chatMessageMapper.toEntity(chatMessage);
        chatMessageEntity.setStatus(ChatMessageStatuses.SENT);
        chatMessageEntity.setCreatedAt(Instant.now());
        chatMessageEntity = chatMessageRepository.save(chatMessageEntity);
        applicationEventPublisher.publishEvent(new IncommingMessageEvent(this, chatMessageEntity));
    }

    @Override
    public Flux<ServerSentEvent> getMessages(String userId) {
        return chatSseManager.createSse(userId);
    }


}
