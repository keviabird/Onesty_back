package com.onesty.api.core.message;

import lombok.Data;

@Data
public class ChatMessage {

    private String toUserId;
    private String fromUserId;
    private String text;
    private String status;
}
