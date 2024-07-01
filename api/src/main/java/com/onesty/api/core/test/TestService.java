package com.onesty.api.core.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TestService {

    @GetMapping("/tests/{testId}/questions")
    List<Question> getQuestionsByTestId(@PathVariable Long testId);
}
