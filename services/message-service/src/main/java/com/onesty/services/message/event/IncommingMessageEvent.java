package com.onesty.services.message.event;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.persistence.ChatMessageEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class IncommingMessageEvent extends ApplicationEvent {

    private final ChatMessageEntity chatMessage;

    public IncommingMessageEvent(Object source, ChatMessageEntity chatMessage) {
        super(source);
        this.chatMessage = chatMessage;
    }
}
