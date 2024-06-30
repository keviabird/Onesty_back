package com.onesty.services.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onesty.api.core.message.ChatMessage;
import com.onesty.api.core.message.MessageService;
import com.onesty.api.exceptions.NotFoundException;
import com.onesty.services.message.event.IncommingMessageEvent;
import com.onesty.services.message.persistence.ChatMessageEntity;
import com.onesty.services.message.persistence.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceManager implements MessageService {

    private final ChatSseManager chatSseManager;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatMessage.setSentAt(Timestamp.valueOf(LocalDateTime.now()));
        var chatMessageEntity = chatMessageMapper.toEntity(chatMessage);
        chatMessageEntity.setStatus(ChatMessageStatuses.SENT);
        chatMessageEntity.setCreatedAt(Instant.now());
        ChatMessageEntity saved = chatMessageRepository.save(chatMessageEntity);
        ChatMessage message = chatMessageMapper.toDto(saved);
        performEventSend(message, chatMessage.getToUserId());
        return message;
    }



    @Override
    public Flux<ServerSentEvent> getMessages(String userId) {
        return chatSseManager.createSse(userId);
    }

    @Override
    public void status(List<String> messageIds) {
        List<ChatMessageEntity> saved = chatMessageRepository.findAllByIdIn(messageIds);
        saved.forEach(message -> message.setStatus(ChatMessageStatuses.READ));
        saved.stream().map(chatMessageMapper::toDto).forEach(message -> performEventSend(message, message.getFromUserId()));
    }

    private void performEventSend(ChatMessage message, String targetUser) {
        try {
            rabbitTemplate.convertAndSend("chatMessageSseExchange", targetUser, objectMapper.writeValueAsString(message));
            rabbitTemplate.convertAndSend("chatExchange", "", objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
