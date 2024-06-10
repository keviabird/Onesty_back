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
public class FullTestSingle {
    private List<QuestionFull> answeredQuestions;
    private List<QuestionFull> nonAnsweredQuestions;
}

