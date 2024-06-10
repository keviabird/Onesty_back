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
public class CategoryShort {
    private Long categoryId;
    private String name;
    private String imageId;
    private List<Rubric> rubrics;
}
