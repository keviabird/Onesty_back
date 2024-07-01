package com.onesty.api.core.test;

import lombok.Data;

@Data
public class TestShort {
    Long id;
    String name;
    String imageUrl;
    String status;
    Integer questionCount;
    Integer answersCount;
    Boolean isLocked;
}
