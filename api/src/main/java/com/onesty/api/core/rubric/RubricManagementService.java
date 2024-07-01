package com.onesty.api.core.rubric;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RubricManagementService {

    @PostMapping("/management/rubrics")
    Rubric createRubric(@RequestBody Rubric rubric);
}
