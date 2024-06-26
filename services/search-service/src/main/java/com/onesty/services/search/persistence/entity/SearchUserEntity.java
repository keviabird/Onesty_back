package com.onesty.services.search.persistence.entity;

import com.onesty.services.search.configuration.MongoDBConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
        @CompoundIndex(def = "{'content.p1': 1, 'content.p2': 1, 'content.p3': 1, 'content.p4': 1, 'content.p5': 1, 'content.p6': 1, 'content.p7': 1, 'content.p8': 1}"),
        @CompoundIndex(def = "{'values.p1': 1, 'values.p2': 1, 'values.p3': 1, 'values.p4': 1, 'values.p5': 1, 'values.p6': 1, 'values.p7': 1, 'values.p8': 1}"),
        @CompoundIndex(def = "{'persona.p1': 1, 'persona.p2': 1, 'persona.p3': 1, 'persona.p4': 1, 'persona.p5': 1, 'persona.p6': 1, 'persona.p7': 1, 'persona.p8': 1}"),
        @CompoundIndex(def = "{'activities.p1': 1, 'activities.p2': 1, 'activities.p3': 1, 'activities.p4': 1, 'activities.p5': 1, 'activities.p6': 1, 'activities.p7': 1, 'activities.p8': 1}"),
        @CompoundIndex(def = "{'views.p1': 1, 'views.p2': 1, 'views.p3': 1, 'views.p4': 1, 'views.p5': 1, 'views.p6': 1, 'views.p7': 1, 'views.p8': 1}")
})
@Document(collection = MongoDBConfiguration.COLLECTION_NAME)
public class SearchUserEntity {

    @Id
    private String id;
    @Version
    private Integer version;

    @Indexed(unique = true)
    private String userId;
    private String name;
    @Indexed(sparse = true)
    private LocalDateTime birthdate;
    @Indexed(sparse = true)
    private String gender;
    private String location;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point coordinates;
    // private List<String> interests;
    @Indexed(sparse = true)
    private Integer socialRating;
    private boolean verifiedProfile;
    private List<String> photos;

    private Ratings content;
    private Ratings values;
    private Ratings persona;
    private Ratings activities;
    private Ratings views;

    // TODO: надо ли хранить дату, тип и режим лайка/дислайка?
    @Indexed
    private Set<String> matchByUserIds = new HashSet<>();
    @Indexed
    private Set<String> mismatchByUserIds = new HashSet<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ratings {

        private Integer p1;
        private Integer p2;
        private Integer p3;
        private Integer p4;
        private Integer p5;
        private Integer p6;
        private Integer p7;
        private Integer p8;
    }

}