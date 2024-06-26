package com.onesty.api.core.match;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class MatchRequest {

    @NotNull
    private MatchType type;
    @NotNull
    private MatchMode mode;
    @NotEmpty
    private String userId;
}