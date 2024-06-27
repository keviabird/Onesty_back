package com.onesty.api.core.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RubricScore {

    private String summary;
    private List<String> commonGround;
    private String leftName;
    private String rightName;
    private Integer personScore;
    private Integer selfScore;
}