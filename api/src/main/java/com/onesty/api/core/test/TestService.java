package com.onesty.api.core.test;

import com.onesty.api.core.user.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface TestService {
    @GetMapping(
            value = "/categories/{categoryId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    CategoryShort getCategory(@PathVariable String categoryId);

    @GetMapping(
            value = "/categories/{categoryId}/rubrics",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<RubricShort> getRubrics(@PathVariable String categoryId);

    @GetMapping(
            value = "/categories/{categoryId}/tests",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<Test> getTests(@PathVariable String categoryId);

    @GetMapping(
            value = "/tests/{testId}/questions",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<Question> getQuestions(@PathVariable String testId);

    @GetMapping(
            value = "/questions/{questionId}/answers",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<Answer> getAnswers(@PathVariable String questionId);

    @GetMapping(
            value = "/categoryresults/{categoryResultId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<CategoryResult> getCategoryResult(@PathVariable String categoryResultId);

    @GetMapping(
            value = "/users/{userId}/categoryresults",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<CategoryResultShort> getCategoryResultByUser(@PathVariable String userId);

    @GetMapping(
            value = "/users/{userId}/testsdomain",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    TestsDomain getTestsDomain(@PathVariable String userId);

    @GetMapping(
            value = "/users/{userId}/tests/{testId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    FullTest getFullTest(@PathVariable String userId, @PathVariable String testId);

    @GetMapping(
            value = "/users/{userId}/tests/{testId}/full",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    FullTestSingle getFullTestWithAnswers(@PathVariable String userId, @PathVariable String testId);

    @GetMapping(
            value = "/users/{userId}/tests/{testId}/testCard",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    TestCard getTestCard(@PathVariable String userId, @PathVariable String testId);

    @PostMapping(
            value = "/users/{userId}/tests/{testId}/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    void saveAnswer(@PathVariable String userId, @PathVariable String testId);
}
