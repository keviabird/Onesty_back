package com.onesty.api.core.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MatchFilter {

    private Range content;
    private Range values;
    private Range persona;
    private Range activities;
    private Range views;
    private Range average;
}
