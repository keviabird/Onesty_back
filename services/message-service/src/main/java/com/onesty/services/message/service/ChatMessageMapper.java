package com.onesty.services.message.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.persistence.ChatMessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessage toDto(ChatMessageEntity chatMessageEntity);

    ChatMessageEntity toEntity(ChatMessage chatMessage);
}
