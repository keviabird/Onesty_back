package com.onesty.services.message.paging.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessageEntity, String> {
    List<ChatMessageEntity> findAllByToUserId(String userId, Pageable pageable);
}
