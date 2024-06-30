package com.onesty.services.chat.service;

import com.onesty.api.core.chat.Chat;
import com.onesty.api.core.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatServiceImpl implements ChatService {

    private final ChatServiceManager chatServiceManager;

    @Override
    public List<Chat> getChats(String userId) {
        return chatServiceManager.getChats(userId);
    }
}
