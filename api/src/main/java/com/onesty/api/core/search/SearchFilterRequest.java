package com.onesty.api.core.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class SearchFilterRequest {

    private String keyword;
    private List<String> interests;
    private Coordinates location;
    private GeneralFilter general;
    private MatchFilter match;
}
