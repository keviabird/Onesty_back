package com.onesty.services.search.controller;

import com.onesty.api.core.match.MatchRequest;
import com.onesty.api.core.match.MatchService;
import com.onesty.services.search.configuration.KafkaConfiguration;
import com.onesty.services.search.service.MatchUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MatchController implements MatchService {

    private final MatchUserService matchUserService;

    @Override
    public void match(String userId, MatchRequest request) {
        log.info("Sending match for userId={}. Request = {}", userId, request);
        matchUserService.send(userId, request);
        log.info("Match was sent for userId={}", userId);
    }

    @KafkaListener(topics = KafkaConfiguration.TOPIC_NAME, idIsGroup = false)
    public void saveMatch(ConsumerRecord<String, MatchRequest> consumerRecord) {
        String userId = consumerRecord.key();
        MatchRequest matchRequest = consumerRecord.value();
        log.info("Saving match for userId={}. Request = {}", userId, matchRequest);
        matchUserService.save(userId, matchRequest);
        log.info("Match was saved for userId={}", userId);
    }

}