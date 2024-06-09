package com.onesty.services.user.service;

import com.onesty.api.core.user.User;
import com.onesty.services.user.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User entityToApi(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserEntity apiToEntity(User api);
}
