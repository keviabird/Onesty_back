package com.onesty.services.message.paging.service;

import com.onesty.api.core.message.ChatMessage;
import com.onesty.api.core.message.MessagePagingService;
import com.onesty.services.message.paging.persistence.ChatMessageEntity;
import com.onesty.services.message.paging.persistence.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessagePagingServiceManager implements MessagePagingService {

    private final ChatMessageRepository repository;
    private final MessageMapper mapper;

    @Override
    public List<ChatMessage> getPagingMessages(String userId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<ChatMessageEntity> allByToUserId = repository.findAllByToUserId(userId, pageable);
        return allByToUserId.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
