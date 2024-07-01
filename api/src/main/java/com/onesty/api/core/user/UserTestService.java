package com.onesty.api.core.user;

import com.onesty.api.core.category.CategoryResult;
import com.onesty.api.core.test.AnsweredQuestionsIds;
import com.onesty.api.core.test.FullTest;
import com.onesty.api.core.test.FullTestWithAnswers;
import com.onesty.api.core.test.TestCard;
import com.onesty.api.core.test.TestDomain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/users")
public interface UserTestService {

    @GetMapping("/{userId}/categoryresults")
    List<CategoryResult> getCategoryResultsByUserId(@PathVariable Long userId);

    @GetMapping("/{userId}/testsdomain")
    TestDomain getTestsDomainByUserId(@PathVariable Long userId);

    @GetMapping("/{userId}/tests/{testId}")
    FullTest getFullTestByUserIdAndTestId(@PathVariable Long userId, @PathVariable Long testId);

    @GetMapping("/{userId}/tests/{testId}/full")
    FullTestWithAnswers getFullTestWithAnswersByUserIdAndTestId(@PathVariable Long userId, @PathVariable Long testId);

    @PostMapping("/{userId}/tests/{testId}/saveAnswers")
    boolean saveAnswersForTest(@PathVariable Long userId, @PathVariable Long testId, @RequestBody List<AnsweredQuestionsIds> answers);

    @GetMapping("/{userId}/tests/{testId}/testCard")
    TestCard getTestCardByUserIdAndTestId(@PathVariable Long userId, @PathVariable Long testId);
}
