package com.onesty.api.core.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class GeneralFilter {

    private String gender;
    private Range ageRange;
    private Range distanceRange;
    private Range socialRatingRange;
    private boolean verifiedProfilesOnly;
}
