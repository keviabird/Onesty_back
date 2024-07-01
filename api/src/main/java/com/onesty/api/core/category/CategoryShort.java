package com.onesty.api.core.category;

import com.onesty.api.core.rubric.RubricShort;
import lombok.Data;

import java.util.List;

@Data
public class CategoryShort {
    Long id;
    String name;
    String imageUrl;
    List<RubricShort> rubrics;
}
