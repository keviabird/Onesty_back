package com.onesty.services.management.service;

import com.onesty.api.core.category.Category;
import com.onesty.api.core.category.CategoryManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryManagementController implements CategoryManagementService {

    private final CategoryRubricTestManager manager;
    @Override
    public Category createCategory(Category category) {
        return manager.createCategory(category);
    }
}
