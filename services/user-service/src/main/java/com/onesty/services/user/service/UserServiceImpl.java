package com.onesty.services.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onesty.api.core.user.User;
import com.onesty.api.core.user.UserDetails;
import com.onesty.api.core.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServiceManager manager;
    private final ObjectMapper mapper;

    @Override
    public User createUser(User body) {
        return manager.createUser(body);
    }

    @Override
    public User getUser(String userId) {
        log.info("got new request with user id {}", userId);
        return manager.getUser(userId);
    }

    @Override
    public User updateUser(Map<String, Object> updates, String userId) {
        return manager.updateUser(updates, userId);
    }

    @Override
    public void deleteUser(String userId) {
        manager.deleteUser(userId);
    }

    @Override
    public UserDetails getUserDetails(String userId) {
        return manager.getUserDetails(userId);
    }

}