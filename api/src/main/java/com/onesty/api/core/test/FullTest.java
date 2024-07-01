package com.onesty.api.core.test;

import lombok.Data;

import java.util.List;

@Data
public class FullTest {
    List<Question> answeredQuestions;
    List<Question> nonAnsweredQuestions;
    List<AnsweredQuestionsIds> questionAndAnswers;
}
