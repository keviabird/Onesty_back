package com.onesty.services.management.service;

import com.onesty.api.core.rubric.Rubric;
import com.onesty.api.core.rubric.RubricManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RubricManagementController implements RubricManagementService {

    private final CategoryRubricTestManager manager;

    @Override
    public Rubric createRubric(Rubric rubric) {
        return manager.createRubric(rubric);
    }
}
