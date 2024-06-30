package com.onesty.services.chat.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends CrudRepository<ChatEntity, String> {
    List<ChatEntity> findAllByToUserId(String toUserId);

    Optional<ChatEntity> findByToUserIdAndFromUserId(String toUserId, String fromUserId);
}
