package com.onesty.services.message.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat_message")
public class ChatMessageEntity {
    @Id
    private String id;

    @Version
    private Integer version;

    private String toUserId;

    private String messageId;

    private String fromUserId;

    private String text;

    private Instant createdAt;

    private String status;
}
