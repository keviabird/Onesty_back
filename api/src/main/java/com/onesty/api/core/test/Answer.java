package com.onesty.api.core.test;

import lombok.Data;

@Data
public class Answer {
    Long id;
    String value;
    Long questionId;
    Long score;
}
