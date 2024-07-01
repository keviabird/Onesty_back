package com.onesty.api.core.rubric;

import lombok.Data;

@Data
public class Rubric {
    Long id;
    String name;
    String imageUrl;
    Long categoryId;
}
