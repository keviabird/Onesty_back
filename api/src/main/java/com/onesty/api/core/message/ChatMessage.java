package com.onesty.api.core.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class ChatMessage {

    private String messageId;
    private String toUserId;
    private String fromUserId;
    private String text;
    private String status;
    private Date sentAt;
}
