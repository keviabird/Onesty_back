package com.onesty.services.search.service;

import com.onesty.api.core.search.Pagination;
import com.onesty.api.core.search.SearchFilterRequest;
import com.onesty.api.core.search.SearchResults;
import com.onesty.api.core.search.User;
import com.onesty.api.exceptions.BadRequestException;
import com.onesty.services.search.mapper.SearchUserMapper;
import com.onesty.services.search.persistence.SearchUserDao;
import com.onesty.services.search.persistence.entity.SearchUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUserService {

    private final SearchUserDao dao;
    private final SearchUserMapper searchUserMapper;

    public SearchResults search(String userId, Integer page, Integer pageSize, SearchFilterRequest request) {
        SearchUserEntity currentUser = dao.findByUserId(userId);
        if (currentUser == null) {
            throw new BadRequestException("Invalid user id");
        }

        Page<SearchUserEntity> results = dao.search(currentUser, page, pageSize, request);
        List<User> users = searchUserMapper.map(results.getContent(), request.getLocation(), currentUser);

        return new SearchResults(new Pagination(results.getNumber(), results.getSize(), Math.toIntExact(results.getTotalElements())), users);
    }

}