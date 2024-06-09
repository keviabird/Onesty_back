package com.onesty.services.user.service;

import com.onesty.api.core.user.User;
import com.onesty.api.core.user.UserService;
import com.onesty.api.exceptions.NotFoundException;
import com.onesty.services.user.persistence.UserEntity;
import com.onesty.services.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class UserServiceManager implements UserService {

    private static final String NOT_FOUND_PREFIX = "No user found for userId: ";
    private Long sequence = 1l;

    private final UserRepository repository;
    private final UserMapper mapper;

    public User createUser(User user) {
        UserEntity userEntity = mapper.apiToEntity(user);
        userEntity.setUserId(sequence);
        sequence++;
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

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    userEntity.setName((String) value);
                    break;
                case "gender":
                    userEntity.setGender((String) value);
                    break;
                case "age":
                    userEntity.setAge((Integer) value);
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
