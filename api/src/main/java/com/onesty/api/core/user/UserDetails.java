package com.onesty.api.core.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserDetails {

    private String userId;
    private String name;
    private Integer age;
    private String gender;
    private String location;
    private String occupation;
    private Integer distance;
    private List<String> interests;
    private Integer socialRating;
    private boolean verifiedProfile;
    private List<String> photos;
    private String aboutMe;
    private Ratings ratings;
    private List<Category> categories;
}