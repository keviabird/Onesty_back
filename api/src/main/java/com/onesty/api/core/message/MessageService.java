package com.onesty.api.core.message;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

public interface MessageService {

    @PostMapping("/send")
    void sendMessage(@RequestBody ChatMessage chatMessage);

    @GetMapping("/new-message/{userId}")
    Flux<ServerSentEvent> getMessages(@PathVariable String userId);
}
