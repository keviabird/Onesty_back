package com.onesty.api.core.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class User {

    private String userId;
    private String name;
    private Integer age;
    private String gender;
    private String location;
    private Integer distance;
    private String occupation;
    private Integer socialRating;
    private boolean verifiedProfile;
    private List<String> photos;
    private Ratings ratings;
}
