package com.onesty.services.search.mapper;

import com.onesty.api.core.search.Ratings;
import com.onesty.services.search.persistence.entity.SearchUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.apache.commons.lang3.ObjectUtils.anyNotNull;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public class RatingsMapper {

    public Ratings calculateRatings(SearchUserEntity user, SearchUserEntity currentUser) {
        Ratings ratings = new Ratings();
        ratings.setContent(calculateCategoryValue(user.getContent(), currentUser.getContent()));
        ratings.setValues(calculateCategoryValue(user.getValues(), currentUser.getValues()));
        ratings.setPersona(calculateCategoryValue(user.getPersona(), currentUser.getPersona()));
        ratings.setActivities(calculateCategoryValue(user.getActivities(), currentUser.getActivities()));
        ratings.setViews(calculateCategoryValue(user.getViews(), currentUser.getViews()));

        if (anyNotNull(ratings.getContent(), ratings.getValues(), ratings.getPersona(), ratings.getActivities(), ratings.getViews())) {
            int sum = defaultIfNull(ratings.getContent()) + defaultIfNull(ratings.getValues()) + defaultIfNull(ratings.getPersona())
                    + defaultIfNull(ratings.getActivities()) + defaultIfNull(ratings.getViews());
            ratings.setAverage(Math.toIntExact(Math.round((double) sum / 5)));
        }
        return ratings;
    }

    private Integer calculateCategoryValue(SearchUserEntity.Ratings ratings, SearchUserEntity.Ratings currentRatings) {
        if (ratings == null || currentRatings == null) {
            return null;
        }

        double score = calculateParameterMatch(defaultIfNull(ratings.getP1()), defaultIfNull(currentRatings.getP1()))
                + calculateParameterMatch(defaultIfNull(ratings.getP2()), defaultIfNull(currentRatings.getP2()))
                + calculateParameterMatch(defaultIfNull(ratings.getP3()), defaultIfNull(currentRatings.getP3()))
                + calculateParameterMatch(defaultIfNull(ratings.getP4()), defaultIfNull(currentRatings.getP4()))
                + calculateParameterMatch(defaultIfNull(ratings.getP5()), defaultIfNull(currentRatings.getP5()))
                + calculateParameterMatch(defaultIfNull(ratings.getP6()), defaultIfNull(currentRatings.getP6()))
                + calculateParameterMatch(defaultIfNull(ratings.getP7()), defaultIfNull(currentRatings.getP7()))
                + calculateParameterMatch(defaultIfNull(ratings.getP8()), defaultIfNull(currentRatings.getP8()));

        score /= 8;
        return Math.toIntExact(Math.round(score));
    }

    private double calculateParameterMatch(int p1, int p2) {
        double value = (double) p1 / p2 * 10;
        return Math.min(value, 10.); // шкала от 0 до 10
    }

    private int defaultIfNull(Integer integer) {
        return integer == null ? 0 : integer;
    }

}