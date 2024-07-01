package com.onesty.api.core.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface QuestionService {

    @GetMapping("/questions/{questionId}/answers")
    List<Answer> getAnswersByQuestionId(@PathVariable Long questionId);
}
