package com.onesty.services.message.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.api.core.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
@RestController
public class ChatServiceImpl implements MessageService {

    private final ChatServiceManager chatServiceManager;
    @Override
    public void sendMessage(ChatMessage chatMessage) {
        chatServiceManager.sendMessage(chatMessage);
    }

    @Override
    public Flux<ServerSentEvent> getMessages(String userId) {
        return chatServiceManager.getMessages(userId);
    }
}
