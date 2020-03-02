package com.pingpong.app.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtils {

    private static final int DEFAULT_ITEMS_PER_PAGE = 9;
    private static final int DEFAULT_PAGE = 1;

    private PageUtils() {
    }

    public static PageRequest buildPageRequest(Integer page, Integer items) {
        var currentPage = (page == null ? DEFAULT_PAGE : page);
        var itemsPerPage = (items == null ? DEFAULT_ITEMS_PER_PAGE : items);
        var offset = currentPage - 1;

        return PageRequest.of(offset, itemsPerPage, Sort.unsorted());
    }
}
