package com.pingpong.app.controllers;

import com.pingpong.app.entities.PingPongTable;
import com.pingpong.app.services.PingPongTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PingPongTableController {

    private static final int DEFAULT_ITEMS_PER_PAGE = 9;
    private static final int DEFAULT_PAGE = 1;

    private final PingPongTableService service;

    @GetMapping(value = "/tables")
    @ResponseStatus(HttpStatus.OK)
    public Page<PingPongTable> findAll(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "items", required = false) Integer items) {

        var pageRequest = buildPageable(page, items);

        return service.findAll(pageRequest);
    }

    private Pageable buildPageable(Integer page, Integer items) {
        var currentPage = (page == null ? DEFAULT_PAGE : page);
        var itemsPerPage = (items == null ? DEFAULT_ITEMS_PER_PAGE : items);
        var offset = currentPage - 1;

        return PageRequest.of(offset, itemsPerPage, Sort.unsorted());
    }
}