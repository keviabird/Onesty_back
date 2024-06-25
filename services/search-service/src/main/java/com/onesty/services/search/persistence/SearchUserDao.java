package com.onesty.services.search.persistence;

import com.onesty.api.core.search.Coordinates;
import com.onesty.api.core.search.GeneralFilter;
import com.onesty.api.core.search.MatchFilter;
import com.onesty.api.core.search.Range;
import com.onesty.api.core.search.SearchFilterRequest;
import com.onesty.services.search.persistence.entity.SearchUserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class SearchUserDao {

    private final MongoOperations mongoOperations;

    public Page<SearchUserEntity> search(SearchUserEntity currentUser, Integer page, Integer pageSize, SearchFilterRequest request) {
        Query query = createSearchQuery(currentUser, request);
        Pageable pageable = PageRequest.of(page, pageSize);
        query.with(pageable);

        List<SearchUserEntity> results = mongoOperations.find(query, SearchUserEntity.class, "search_user");

        return PageableExecutionUtils.getPage(results, pageable, () -> totalCount(query));
    }

    public SearchUserEntity findByUserId(String userId) {
        Criteria criteria = where("userId").is(userId);
        Query query = Query.query(criteria);
        return mongoOperations.findOne(query, SearchUserEntity.class, "search_user");
    }

    private Query createSearchQuery(SearchUserEntity currentUser, SearchFilterRequest request) {
        Query query = new Query();
        query.fields().exclude("matchByUserIds", "mismatchByUserIds");
        query = fillGeneralFilter(query, request);
        query = fillMatchFilter(query, currentUser, request);
        String userId = currentUser.getUserId();
        query = query.addCriteria(
                where("userId").ne(userId)
                        .and("matchByUserIds").ne(userId)
                        .and("mismatchByUserIds").ne(userId));
        return query;
    }

    private Query fillGeneralFilter(Query query, SearchFilterRequest request) {
        GeneralFilter general = request.getGeneral();
        Query tmp = query;
        if (general == null) {
            return tmp;
        }
        String gender = general.getGender();
        if (StringUtils.isNotBlank(gender)) {
            Criteria genderCriteria = where("gender").is(gender);
            tmp = tmp.addCriteria(genderCriteria);
        }

        Range ageRange = general.getAgeRange();
        if (ageRange != null && ageRange.getMax() >= ageRange.getMin()) {
            LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime minAgeDate = now.minusYears(ageRange.getMin());
            LocalDateTime maxAgeDate = now.minusYears(ageRange.getMax());
            Criteria ageCriteria = where("birthdate").gte(maxAgeDate).lte(minAgeDate);
            tmp = tmp.addCriteria(ageCriteria);
        }

        Range distanceRange = general.getDistanceRange();
        if (distanceRange != null && distanceRange.getMax() >= distanceRange.getMin()) {
            Coordinates location = request.getLocation();
            if (location != null) {
                Point currentLocation = new Point(location.getLongitude(), location.getLatitude());
                Circle maxCircle = new Circle(currentLocation, new Distance(distanceRange.getMax(), Metrics.KILOMETERS));
                Circle minCircle = new Circle(currentLocation, new Distance(distanceRange.getMin(), Metrics.KILOMETERS));
                Criteria distanceCriteria = new Criteria().andOperator(
                        where("coordinates").not().withinSphere(minCircle),
                        where("coordinates").withinSphere(maxCircle)
                );
                tmp = tmp.addCriteria(distanceCriteria);
            }
        }

        Range socialRatingRange = general.getSocialRatingRange();
        if (socialRatingRange != null && socialRatingRange.getMax() >= socialRatingRange.getMin()) {
            Criteria ratingCriteria = where("socialRating").gte(socialRatingRange.getMin()).lte(socialRatingRange.getMax());
            tmp = tmp.addCriteria(ratingCriteria);
        }

        Boolean verifiedProfilesOnly = general.getVerifiedProfilesOnly();
        if (verifiedProfilesOnly != null && verifiedProfilesOnly) {
            Criteria verifiedCriteria = where("verifiedProfile").is(true);
            tmp = tmp.addCriteria(verifiedCriteria);
        }

        return tmp;
    }

    private Query fillMatchFilter(Query query, SearchUserEntity currentUser, SearchFilterRequest request) {
        MatchFilter match = request.getMatch();
        Query tmp = query;
        if (match == null) {
            return tmp;
        }

        tmp = addMatchFilterCategoryCriteria(tmp, "content", currentUser.getContent(), match.getContent());
        tmp = addMatchFilterCategoryCriteria(tmp, "values", currentUser.getValues(), match.getValues());
        tmp = addMatchFilterCategoryCriteria(tmp, "persona", currentUser.getPersona(), match.getPersona());
        tmp = addMatchFilterCategoryCriteria(tmp, "activities", currentUser.getActivities(), match.getActivities());
        tmp = addMatchFilterCategoryCriteria(tmp, "views", currentUser.getViews(), match.getViews());
        // TODO: что такое average и как оно сочетается с фильтрами по категориям?

        return tmp;
    }

    private Query addMatchFilterCategoryCriteria(Query query, String category, SearchUserEntity.Ratings currentRatings, Range range) {
        if (currentRatings == null || range == null || range.getMin() > range.getMax()) {
            return query;
        }
        Query tmp = query;
        Integer p1 = currentRatings.getP1();
        String key = category + ".p1";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p1, range);

        Integer p2 = currentRatings.getP2();
        key = category + ".p2";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p2, range);

        Integer p3 = currentRatings.getP3();
        key = category + ".p3";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p3, range);

        Integer p4 = currentRatings.getP4();
        key = category + ".p4";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p4, range);

        Integer p5 = currentRatings.getP5();
        key = category + ".p5";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p5, range);

        Integer p6 = currentRatings.getP6();
        key = category + ".p6";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p6, range);

        Integer p7 = currentRatings.getP7();
        key = category + ".p7";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p7, range);

        Integer p8 = currentRatings.getP8();
        key = category + ".p8";
        tmp = addMatchCategoryParameterCriteria(tmp, key, p8, range);

        return tmp;
    }

    private Query addMatchCategoryParameterCriteria(Query query, String key, Integer parameterValue, Range range) {
        if (parameterValue != null) {
            // Criteria ratingCriteria = new Criteria().orOperator(
            //         where(key).isNull(),
            //         where(key).gte(parameterValue * range.getMin()).lte(parameterValue * range.getMax())
            // ); TODO: переделать запрос на сочетание or и and операторов
            Criteria ratingCriteria = where(key).gte(parameterValue * range.getMin() / 10).lte(parameterValue * range.getMax() / 10);
            query = query.addCriteria(ratingCriteria);
        }
        return query;
    }

    private long totalCount(Query query) {
        Query countQuery = Query.of(query).skip(-1).limit(-1);
        return mongoOperations.count(countQuery, SearchUserEntity.class, "search_user");
    }

}