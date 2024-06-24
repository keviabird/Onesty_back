package com.onesty.services.message.listener;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.event.IncommingMessageEvent;
import com.onesty.services.message.exception.NoActiveSseSubscriberException;
import com.onesty.services.message.persistence.ChatMessageEntity;
import com.onesty.services.message.persistence.ChatMessageRepository;
import com.onesty.services.message.service.ChatMessageMapper;
import com.onesty.services.message.service.ChatMessageStatuses;
import com.onesty.services.message.service.ChatSseManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class IncommingMessageListener implements ApplicationListener<IncommingMessageEvent> {

    private final ChatSseManager chatSseManager;
    private final ChatMessageMapper chatMessageMapper;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void onApplicationEvent(IncommingMessageEvent event) {
        log.info("Got new message event");
        event.getChatMessage().setStatus(ChatMessageStatuses.DELIVERED);
        ChatMessageEntity saved = chatMessageRepository.save(event.getChatMessage());
        log.info("new message id is {}", saved.getId());
        ChatMessage message = chatMessageMapper.toDto(saved);
        log.info("converted message id is {}", message.getMessageId());
        try {
            chatSseManager.sendSseMessage(message);
        } catch (NoActiveSseSubscriberException e){
            return;
        }

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
