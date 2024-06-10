package com.onesty.services.test.service;

import com.onesty.api.core.test.Answer;
import com.onesty.api.core.test.CategoryResult;
import com.onesty.api.core.test.CategoryResultShort;
import com.onesty.api.core.test.CategoryShort;
import com.onesty.api.core.test.FullTest;
import com.onesty.api.core.test.FullTestSingle;
import com.onesty.api.core.test.Question;
import com.onesty.api.core.test.RubricShort;
import com.onesty.api.core.test.Test;
import com.onesty.api.core.test.TestCard;
import com.onesty.api.core.test.TestService;
import com.onesty.api.core.test.TestsDomain;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestServiceImpl implements TestService {
    @Override
    public CategoryShort getCategory(String categoryId) {
        return null;
    }

    @Override
    public List<RubricShort> getRubrics(String categoryId) {
        return null;
    }

    @Override
    public List<Test> getTests(String categoryId) {
        return null;
    }

    @Override
    public List<Question> getQuestions(String testId) {
        return null;
    }

    @Override
    public List<Answer> getAnswers(String questionId) {
        return null;
    }

    @Override
    public List<CategoryResult> getCategoryResult(String categoryResultId) {
        return null;
    }

    @Override
    public List<CategoryResultShort> getCategoryResultByUser(String userId) {
        return null;
    }

    @Override
    public TestsDomain getTestsDomain(String userId) {
        return null;
    }

    @Override
    public FullTest getFullTest(String userId, String testId) {
        return null;
    }

    @Override
    public FullTestSingle getFullTestWithAnswers(String userId, String testId) {
        return null;
    }

    @Override
    public TestCard getTestCard(String userId, String testId) {
        return null;
    }

    @Override
    public void saveAnswer(String userId, String testId) {

    }
}
