package com.onesty.services.message.paging.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessageEntity, String> {

    @Query("$and: [{'toUserId' : :#{#toUserId}}, {'fromUserId' : :#{#toUserId}}]")
    List<ChatMessageEntity> findAllByToUserId(@Param("toUserId") String toUserId, Pageable pageable);

   // List<ChatMessageEntity> findAllByToUserId(String userId, Pageable pageable);
}
