package com.onesty.services.search.mapper;

import com.onesty.api.core.search.Coordinates;
import com.onesty.api.core.search.User;
import com.onesty.services.search.persistence.entity.SearchUserEntity;
import com.onesty.services.search.util.DistanceUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class SearchUserMapper {

    @Autowired
    protected RatingsMapper ratingsMapper;

    public List<User> map(List<SearchUserEntity> users, Coordinates currentLocation, SearchUserEntity currentUser) {
        if (users == null) {
            return List.of();
        }
        return users.stream()
                .map(entity -> map(entity, currentLocation, currentUser))
                .filter(Objects::nonNull)
                .toList();
    }

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "age", source = "user.birthdate", qualifiedByName = "dateToAge")
    @Mapping(target = "gender", source = "user.gender")
    @Mapping(target = "location", source = "user.location")
    @Mapping(target = "distance", expression = "java(calculateDistance(currentLocation, user.getCoordinates()))")
    @Mapping(target = "interests", ignore = true)
    @Mapping(target = "socialRating", source = "user.socialRating")
    @Mapping(target = "verifiedProfile", source = "user.verifiedProfile")
    @Mapping(target = "photos", source = "user.photos")
    @Mapping(target = "ratings", expression = "java(ratingsMapper.calculateRatings(user, currentUser))")
    public abstract User map(SearchUserEntity user, Coordinates currentLocation, SearchUserEntity currentUser);

    @Named("dateToAge")
    protected Integer toAge(LocalDateTime birthdate) {
        if (birthdate == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        return Math.toIntExact(ChronoUnit.YEARS.between(birthdate, now));
    }

    protected Integer calculateDistance(Coordinates currentLocation, Point location) {
        if (currentLocation == null || location == null) {
            return null;
        }

        Point current = new Point(currentLocation.getLongitude(), currentLocation.getLatitude());
        double distance = DistanceUtils.haversineDistance(current, location);
        return (int) Math.round(Math.ceil(distance));
    }

}