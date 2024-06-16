package com.onesty.services.message.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessageEntity, String> {
    List<ChatMessageEntity> findAllByToUserId(String userId);
}
