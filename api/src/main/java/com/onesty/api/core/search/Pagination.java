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
public class Pagination {

    private int currentPage;
    private int pageSize;
    private int totalResults;
}
