package com.onesty.services.user.service;

import com.onesty.api.core.user.User;
import com.onesty.api.core.user.UserService;
import com.onesty.api.exceptions.NotFoundException;
import com.onesty.services.user.persistence.UserEntity;
import com.onesty.services.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceManager implements UserService {

    private static final String NOT_FOUND_PREFIX = "No user found for userId: ";

    private final UserRepository repository;
    private final UserMapper mapper;

    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = mapper.apiToEntity(user);
        LocalDateTime now = LocalDateTime.now();
        userEntity.setCreatedAt(now);
        userEntity.setUpdatedAt(now);
        UserEntity saved = repository.save(userEntity);
        return mapper.entityToApi(saved);
    }

    public User getUser(String userId) {
        UserEntity userEntity = repository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PREFIX + userId));
        if (userEntity.getDeletedAt() != null) throw new NotFoundException(NOT_FOUND_PREFIX + userId);
        return mapper.entityToApi(userEntity);
    }

    public User updateUser(Map<String, Object> updates, String userId) {
        UserEntity userEntity = repository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PREFIX + userId));
        if (updates.containsKey("birthdate")) {
            Integer birthdate = (Integer) updates.get("birthdate");
            log.info("{}", birthdate);
            LocalDateTime converted = LocalDateTime.ofInstant(Instant.ofEpochSecond(birthdate), ZoneId.systemDefault());
            log.info("birthdate original is {}, converted is {}", birthdate, converted);
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    userEntity.setName((String) value);
                    break;
                case "gender":
                    userEntity.setGender((String) value);
                    break;
                case "birthdate":
                    userEntity.setBirthdate(LocalDateTime.ofInstant(Instant.ofEpochSecond((Integer) value), ZoneId.systemDefault()));
                    break;
                case "height":
                    userEntity.setHeight((Integer) value);
                    break;
                case "weight":
                    userEntity.setWeight((Integer) value);
                    break;
                case "location":
                    userEntity.setLocation((String) value);
                    break;
                case "profileName":
                    userEntity.setProfileName((String) value);
                    break;
                case "occupation":
                    userEntity.setOccupation((String) value);
                    break;
                case "languages":
                    userEntity.setLanguages((List<String>) value);
                    break;
                case "education":
                    userEntity.setEducation((String) value);
                    break;
                case "relationship":
                    userEntity.setRelationship((String) value);
                    break;
                case "sports":
                    userEntity.setSports((String) value);
                    break;
                case "alcohol":
                    userEntity.setAlcohol((String) value);
                    break;
                case "smoking":
                    userEntity.setSmoking((String) value);
                    break;
                case "drugs":
                    userEntity.setDrugs((String) value);
                    break;
                case "activity":
                    userEntity.setActivity((String) value);
                    break;
                case "aboutMe":
                    userEntity.setAboutMe((String) value);
                    break;
                case "photos":
                    userEntity.setPhotos((List<String>) value);
                    break;
                case "fieldsToHide":
                    userEntity.setFieldsToHide((List<String>) value);
                    break;
                default:
                    break;
            }
        });
        userEntity.setUpdatedAt(LocalDateTime.now());
        UserEntity saved = repository.save(userEntity);
        return mapper.entityToApi(saved);
    }

    public void deleteUser(String userId) {
        UserEntity userEntity = repository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PREFIX + userId));
        userEntity.setDeletedAt(now());
        repository.save(userEntity);
    }
}
