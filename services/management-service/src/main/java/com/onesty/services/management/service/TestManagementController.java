package com.onesty.services.management.service;

import com.onesty.api.core.test.TestManagement;
import com.onesty.api.core.test.TestManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestManagementController implements TestManagementService {

    private final CategoryRubricTestManager manager;
    @Override
    public TestManagement createTest(TestManagement test) {
        return manager.createTest(test);
    }
}
