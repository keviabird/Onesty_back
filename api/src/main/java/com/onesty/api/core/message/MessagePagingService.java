package com.onesty.api.core.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MessagePagingService {

    @GetMapping("/messages/{userId}")
    List<ChatMessage> getPagingMessages(@PathVariable String userId,
                                        @RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize);
}
