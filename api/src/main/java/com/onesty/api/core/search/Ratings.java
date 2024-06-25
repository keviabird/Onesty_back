package com.onesty.api.core.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class Ratings {

    private Integer content;
    private Integer values;
    private Integer persona;
    private Integer activities;
    private Integer views;
    private Integer average;
}
