package com.onesty.services.message.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.message.persistence.ChatMessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    @Mapping(source = "id", target = "messageId")
    @Mapping(source = "createdAt", target = "sentAt")
    ChatMessage toDto(ChatMessageEntity chatMessageEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "sentAt", target = "createdAt")
    @Mapping(target = "version", ignore = true)
    ChatMessageEntity toEntity(ChatMessage chatMessage);

    default Timestamp map(Instant instant) {return Timestamp.from(instant);}

    default Instant map(Timestamp timestamp) {return timestamp.toInstant();}
}
