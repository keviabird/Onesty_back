package com.onesty.services.user.service;

import com.onesty.api.core.user.User;
import com.onesty.services.user.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    User entityToApi(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    UserEntity apiToEntity(User api);

    default Timestamp map(LocalDateTime original) {
        return Timestamp.valueOf(original);
    }

    default LocalDateTime map (Timestamp original) {
        return original.toLocalDateTime();
    }
}
