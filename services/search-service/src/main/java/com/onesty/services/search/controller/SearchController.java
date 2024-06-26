package com.onesty.services.search.controller;

import com.onesty.api.core.search.SearchFilterRequest;
import com.onesty.api.core.search.SearchResults;
import com.onesty.api.core.search.SearchService;
import com.onesty.services.search.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class SearchController implements SearchService {

    private final SearchUserService searchService;

    @Override
    public SearchResults search(Integer page, Integer pageSize, String userId, SearchFilterRequest request) {
        log.info("Search for userId={}, page={}, pageSize={}. Request = {}", userId, page, pageSize, request);

        // TODO: add validation of the request
        SearchResults results = searchService.search(userId, page, pageSize, request);

        log.info("Search results for userId={} - {}", userId, results);
        return results;
    }
}
