package com.onesty.api.core.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TestCard {

    private String firstValue;
    private String imageId;
    private String secondValue;
    private Description description;
}
