package com.onesty.api.core.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class FullTest {
    private List<Question> answeredQuestions;
    private List<Question> nonAnsweredQuestions;
    private List<QuestionAndAnswer> questionAndAnswers;
}