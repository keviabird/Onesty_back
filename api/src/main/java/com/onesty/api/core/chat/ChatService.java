package com.onesty.api.core.chat;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {
    @GetMapping("/chat/{userId}")
    List<Chat> getChats(@PathVariable String userId);
}
