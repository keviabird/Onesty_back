package com.onesty.api.core.test;

import lombok.Data;

import java.util.List;

@Data
public class TestCard {
    String firstValue;
    String imageUrl;
    String secondValue;
    Description description;

    @Data
    public static class Description {
        String value;
        List<ParameterInfo> parameterInfo;

        @Data
        public static class ParameterInfo {
            String value;
            Integer score;
            String color;
        }
    }
}
