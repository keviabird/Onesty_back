package com.onesty.api.core.chat;

import com.onesty.api.core.message.ChatMessage;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Chat {
    private String toUserId;
    private Timestamp lastMessageDate;
    private ChatMessage lastMessage;
}
