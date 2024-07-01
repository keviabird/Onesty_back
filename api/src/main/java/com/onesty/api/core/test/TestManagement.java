package com.onesty.api.core.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TestManagement {

    Long testId;
    String name;
    String imageUrl;
    List<Question> questions;
    List<Answer> answers;
    Boolean isBlocked;
    Long rubricId;
}
