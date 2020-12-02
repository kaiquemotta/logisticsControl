package com.logistica.model;

import org.springframework.data.domain.Sort;

public interface Pageable {
    // returns the current page number (zero-based)
    int getPageNumber();

    // returns the size of the page
    int getPageSize();

    // returns the sorting parameters
    Sort getSort();

    // ... other methods
}
