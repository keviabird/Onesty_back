package com.onesty.api.core.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Test {

    private String testId;
    private String name;
    private String imageId;
    private String status;
    private Long questionCount;
    private Long answersCount;
    private Boolean isLocked;
}
