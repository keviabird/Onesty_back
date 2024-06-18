package com.onesty.services.message.paging.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.paging.persistence.ChatMessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    ChatMessage toDto(ChatMessageEntity chatMessageEntity);

    ChatMessageEntity toEntity(ChatMessage chatMessage);
}
