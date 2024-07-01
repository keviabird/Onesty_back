package com.onesty.api.core.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryResultService {

    @GetMapping("/categoryresults/{categoryResultId}")
    FullCategoryResult getFullCategoryResultById(@PathVariable Long categoryResultId);
}
