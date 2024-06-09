package com.onesty.api.core.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface UserService {


    @PostMapping(
            value = "/users",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    User createUser(@RequestBody User body);
    @GetMapping(
            value = "users/{userId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    User getUser(@PathVariable Long userId);

    @PatchMapping(
            value = "users/{userId}",
            produces = APPLICATION_JSON_VALUE
    )
    User updateUser(@RequestBody Map<String, Object> updates,
                    @PathVariable Long userId);

    @DeleteMapping(
            value = "users/{userId}",
            produces = APPLICATION_JSON_VALUE
    )
    void deleteUser(@PathVariable Long userId);
}
