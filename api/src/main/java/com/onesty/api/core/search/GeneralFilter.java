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
public class GeneralFilter {

    private List<String> gender;
    private Range ageRange;
    private Range distanceRange;
    private Range socialRatingRange;
    private Boolean verifiedProfilesOnly;
}
