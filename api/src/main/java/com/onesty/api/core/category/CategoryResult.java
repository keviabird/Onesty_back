package com.onesty.api.core.category;

import lombok.Data;

@Data
public class CategoryResult {
    Long id;
    Long userId;
    Long categoryId;
    String content;
}
