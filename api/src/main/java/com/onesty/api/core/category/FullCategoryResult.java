package com.onesty.api.core.category;

import com.onesty.api.core.test.Answer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FullCategoryResult extends CategoryResultWithParameters {
    List<Answer> answers;
}
