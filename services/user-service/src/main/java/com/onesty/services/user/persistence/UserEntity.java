package com.onesty.services.user.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    @Version
    private Integer version;
    @Indexed(unique = true)
    private String userId;
    private String name;
    private String gender;
    private LocalDateTime age;
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
    private List<String> fieldsToHide;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}