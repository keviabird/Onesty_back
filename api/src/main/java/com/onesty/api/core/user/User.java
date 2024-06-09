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
public class User {

    private Long userId;
    private String name;
    private String gender;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String location;
    private String profileName;
    private String occupation;
    private List<String> languages;
    private String education;
    private String relationship;
    private String sports;
    private String alcohol;
    private String smoking;
    private String drugs;
    private String activity;
    private String aboutMe;
    private List<String> photos;
}
