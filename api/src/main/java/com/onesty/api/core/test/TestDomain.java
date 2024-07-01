package com.onesty.api.core.test;

import com.onesty.api.core.category.CategoryShort;
import lombok.Data;

import java.util.List;

@Data
public class TestDomain {
    List<CategoryShort> categories;
}
