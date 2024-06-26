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
public class MatchFilter {

    private Range content;
    private Range values;
    private Range persona;
    private Range activities;
    private Range views;
    private Range average;
}
