package com.onesty.api.core.category;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoryManagementService {

    @PostMapping("/management/category")
    Category createCategory(@RequestBody Category category);
}
