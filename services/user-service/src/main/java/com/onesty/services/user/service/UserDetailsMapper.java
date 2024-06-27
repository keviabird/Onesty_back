package com.onesty.services.user.service;

import com.onesty.api.core.user.UserDetails;
import com.onesty.services.user.persistence.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDetailsMapper {

    @Mapping(target = "age", source = "birthdate", qualifiedByName = "dateToAge")
    @Mapping(target = "distance", ignore = true)
    @Mapping(target = "interests", ignore = true)
    @Mapping(target = "socialRating", ignore = true)
    @Mapping(target = "verifiedProfile", constant = "true")
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "categories", ignore = true)
    UserDetails entityToApi(UserEntity entity);

    @Named("dateToAge")
    default Integer dateToAge(LocalDateTime birthdate) {
        LocalDateTime now = LocalDateTime.now();
        return Math.toIntExact(ChronoUnit.YEARS.between(birthdate, now));
    }

    @AfterMapping
    default UserDetails hideFields(@MappingTarget UserDetails dto, UserEntity entity) {
        Set<String> fieldsToHide = entity.getFieldsToHide();
        for (String field : fieldsToHide) {
            switch (field) {
                case "birthdate":
                    dto.setAge(null);
                    break;
                case "location":
                    dto.setLocation(null);
                    break;
                case "occupation":
                    dto.setOccupation(null);
                    break;
                case "aboutMe":
                    dto.setAboutMe(null);
                    break;
                default:
                    break;
            }
        }
        return dto;
    }

}