package com.onesty.services.message.paging.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.api.core.message.MessagePagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessagePagingServiceImpl implements MessagePagingService {

    private final MessagePagingServiceManager manager;
    @Override
    public List<ChatMessage> getPagingMessages(String userId, Integer pageNum, Integer pageSize) {
        return manager.getPagingMessages(userId, pageNum, pageSize);
    }
}
