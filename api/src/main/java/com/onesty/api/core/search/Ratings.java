package com.onesty.api.core.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Ratings {

    private Integer content;
    private Integer values;
    private Integer persona;
    private Integer activities;
    private Integer views;
}
