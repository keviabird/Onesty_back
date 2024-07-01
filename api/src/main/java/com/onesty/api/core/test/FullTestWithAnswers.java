package com.onesty.api.core.test;

import lombok.Data;

import java.util.List;

@Data
public class FullTestWithAnswers {
    List<QuestionWithAnswers> answeredQuestions;
    List<QuestionWithAnswers> nonAnsweredQuestions;
}
