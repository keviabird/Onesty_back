package com.onesty.api.core.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryResult {
    private Long categoryResultId;
    private String userId;
    private Long categoryId;
    private String content;
    private List<Answer> answers;
    private List<Parameter> parameters;
}