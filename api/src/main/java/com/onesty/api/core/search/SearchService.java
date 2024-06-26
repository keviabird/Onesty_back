package com.onesty.api.core.search;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// TODO: переделать на реактивный
public interface SearchService {

    @PostMapping(
            value = "/users/{userId}/search",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    SearchResults search(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                         @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                         @PathVariable(name = "userId") @NotEmpty String userId,
                         @RequestBody @Valid SearchFilterRequest request);

}
