package com.onesty.services.search.service;

import com.onesty.api.core.match.MatchRequest;
import com.onesty.services.search.persistence.SearchUserDao;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.onesty.services.search.configuration.KafkaConfiguration.TOPIC_NAME;

@Service
@RequiredArgsConstructor
public class MatchUserService {

    private final KafkaOperations<String, MatchRequest> kafkaOperations;
    private final SearchUserDao matchUserDao;

    public void send(String userId, MatchRequest request) {
        Headers headers = new RecordHeaders()
                .add("onesty-match-mode", request.getMode().toString().getBytes(StandardCharsets.UTF_8))
                .add("onesty-match-type", request.getType().toString().getBytes(StandardCharsets.UTF_8));
        ProducerRecord<String, MatchRequest> producerRecord = new ProducerRecord<>(TOPIC_NAME, null, userId, request, headers);
        kafkaOperations.send(producerRecord);
    }

    public void save(String userId, MatchRequest request) {
        matchUserDao.saveMatch(userId, request);
    }

}