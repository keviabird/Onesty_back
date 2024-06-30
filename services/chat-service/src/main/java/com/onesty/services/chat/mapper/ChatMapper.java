package com.onesty.services.chat.mapper;

import com.onesty.api.core.chat.Chat;
import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.chat.persistence.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    Chat toDto(ChatEntity chatEntity);

    default Timestamp map(Instant instant) {return Timestamp.from(instant);}

    default Instant map(Timestamp timestamp) {return timestamp.toInstant();}
}
