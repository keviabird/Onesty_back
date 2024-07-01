package com.onesty.api.core.category;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryResultWithParameters extends CategoryResult {
    List<Parameter> parameters;
}
