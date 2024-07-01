package com.onesty.services.chat.persistence;

import com.onesty.api.core.message.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat")
public class ChatEntity {

    @Id
    private String id;

    @Version
    private Integer version;

    private String fromUserId;
    private String toUserId;
    private Date lastMessageDate;
    private ChatMessage lastMessage;
}
