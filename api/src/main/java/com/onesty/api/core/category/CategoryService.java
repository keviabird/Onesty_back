package com.onesty.api.core.category;

import com.onesty.api.core.rubric.Rubric;
import com.onesty.api.core.test.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/categories")
public interface CategoryService {

    @GetMapping("/{categoryId}")
    Category getCategoryById(@PathVariable Long categoryId);

    @GetMapping("/{categoryId}/rubrics")
    List<Rubric> getRubricsByCategoryId(@PathVariable Long categoryId);

    @GetMapping("/{categoryId}/tests")
    List<Test> getTestsByCategoryId(@PathVariable Long categoryId);
}
