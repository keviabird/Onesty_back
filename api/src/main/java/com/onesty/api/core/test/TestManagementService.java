package com.onesty.api.core.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TestManagementService {

    @PostMapping("/management/tests")
    TestManagement createTest(@RequestBody TestManagement test);
}
