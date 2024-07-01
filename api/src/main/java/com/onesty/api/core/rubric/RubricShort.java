package com.onesty.api.core.rubric;

import com.onesty.api.core.test.TestShort;
import lombok.Data;

import java.util.List;

@Data
public class RubricShort {
    Long id;
    String name;
    String imageUrl;
    List<TestShort> tests;
}
