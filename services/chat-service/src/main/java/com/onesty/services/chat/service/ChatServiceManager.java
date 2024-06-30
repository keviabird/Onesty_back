package com.onesty.services.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onesty.api.core.chat.Chat;
import com.onesty.api.core.chat.ChatService;
import com.onesty.api.core.message.ChatMessage;
import com.onesty.services.chat.mapper.ChatMapper;
import com.onesty.services.chat.persistence.ChatEntity;
import com.onesty.services.chat.persistence.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ChatServiceManager implements ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    @Override
    public List<Chat> getChats(String userId) {
        return chatRepository.findAllByToUserId(userId).stream().map(chatMapper::toDto).toList();
    }

    @RabbitListener(queues = "chatQueue")
    public void messageListener(String message) throws JsonProcessingException {
        var receivedMessage = objectMapper.readValue(message, ChatMessage.class);
        var chat = chatRepository.findByToUserIdAndFromUserId(receivedMessage.getToUserId(), receivedMessage.getFromUserId());
        if (chat.isEmpty()){
           createChat(receivedMessage);
           return;
        }
        chat.ifPresent(c -> processChatWithNewMessage(c, receivedMessage));
        chatRepository.save(chat.get());
    }

    private void processChatWithNewMessage(ChatEntity chat, ChatMessage chatMessage){
        chat.setLastMessage(chatMessage);
        chat.setLastMessageDate(chatMessage.getSentAt());
    }

    private void createChat(ChatMessage chatMessage){
        var chatEntity = new ChatEntity();
        chatEntity.setFromUserId(chatMessage.getFromUserId());
        chatEntity.setToUserId(chatMessage.getToUserId());
        chatEntity.setLastMessage(chatMessage);
        chatEntity.setLastMessageDate(chatMessage.getSentAt());
        chatRepository.save(chatEntity);
    }

}
