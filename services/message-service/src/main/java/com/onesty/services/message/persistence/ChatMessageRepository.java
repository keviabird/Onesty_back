package com.onesty.services.message.persistence;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessageEntity, String> {

    @Query("$and: [{'toUserId' : :#{#toUserId}}, {'fromUserId' : :#{#toUserId}}]")
    List<ChatMessageEntity> findAllByToUserId(@Param("toUserId") String toUserId);

    @Query("$and: [{'toUserId' : :#{#toUserId}, 'fromUserId' : :#{#fromUserId}}, {'toUserId' : :#{#fromUserId}, 'fromUserId' : :#{#toUserId}}]")
    List<ChatMessageEntity> findAllMessages(@Param("toUserId") String toUserId, @Param("fromUserId") String fromUserId);

    List<ChatMessageEntity> findAllByIdIn(List<String> ids);
}
