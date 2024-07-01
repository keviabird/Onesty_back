package com.onesty.services.management.service;

import com.onesty.api.core.category.Category;
import com.onesty.api.core.category.CategoryManagementService;
import com.onesty.api.core.rubric.Rubric;
import com.onesty.api.core.rubric.RubricManagementService;
import com.onesty.api.core.test.Answer;
import com.onesty.api.core.test.Question;
import com.onesty.api.core.test.TestManagement;
import com.onesty.api.core.test.TestManagementService;
import com.onesty.services.management.persistence.AnswerEntity;
import com.onesty.services.management.persistence.AnswerRepository;
import com.onesty.services.management.persistence.QuestionEntity;
import com.onesty.services.management.persistence.QuestionRepository;
import com.onesty.services.management.persistence.TestEntity;
import com.onesty.services.management.persistence.TestRepository;
import com.onesty.services.management.service.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryRubricTestManager implements CategoryManagementService, RubricManagementService, TestManagementService {

    private final TestMapper testMapper;
    private final TestRepository testRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Rubric createRubric(Rubric rubric) {
        return null;
    }

    @Override
    public TestManagement createTest(TestManagement test) {
        TestEntity testEntity = testMapper.apiToEntity(test);
        TestEntity savedTest = testRepository.save(testEntity);
        List<QuestionEntity> questionEntities = test.
                getQuestions().
                stream().
                map(testMapper::apiToEntity).
                toList();
        List<Question> savedQuestions = questionEntities.
                stream().
                map(questionRepository::save).
                map(testMapper::entityToApi).
                collect(Collectors.toList());
        List<AnswerEntity> answerEntities = test.
                getAnswers().
                stream().
                map(testMapper::apiToEntity).
                toList();
        List<Answer> savedAnswers = answerEntities.
                stream().
                map(answerRepository::save).
                map(testMapper::entityToApi).
                collect(Collectors.toList());
        return new TestManagement(savedTest.getId(),
                savedTest.getName(),
                savedTest.getImageUrl(),
                savedQuestions,
                savedAnswers,
                savedTest.getIsBlocked(),
                savedTest.getRubricId()
                );
    }
}
