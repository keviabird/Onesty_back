package com.onesty.api.core.match;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// TODO: переделать на реактивный
public interface MatchService {

    @PostMapping(
            value = "/users/{userId}/match",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    void match(@PathVariable(name = "userId") @NotEmpty String userId, @RequestBody @Valid MatchRequest request);

}