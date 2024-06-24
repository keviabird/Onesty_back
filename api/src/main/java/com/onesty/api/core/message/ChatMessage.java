package com.onesty.api.core.message;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatMessage {

    private String messageId;
    private String toUserId;
    private String fromUserId;
    private String text;
    private String status;
    private Timestamp sentAt;
}
