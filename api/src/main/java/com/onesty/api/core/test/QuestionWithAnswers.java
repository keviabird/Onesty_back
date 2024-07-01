package com.onesty.api.core.test;

import lombok.Data;

import java.util.List;

@Data
public class QuestionWithAnswers {
    Long id;
    String value;
    List<Answer> answers;
    Long answeredId;
}
