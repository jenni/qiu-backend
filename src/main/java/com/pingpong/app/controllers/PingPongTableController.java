package com.pingpong.app.controllers;

import com.pingpong.app.dtos.PingPongTableDto;
import com.pingpong.app.entities.PingPongTable;
import com.pingpong.app.services.PingPongTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PingPongTableController {

    private static final int DEFAULT_ITEMS_PER_PAGE = 9;
    private static final int DEFAULT_PAGE = 1;

    private final PingPongTableService service;

    @GetMapping(value = "/ping-pong-tables")
    @ResponseStatus(HttpStatus.OK)
    public Page<PingPongTable> findAll(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "items", required = false) Integer items) {

        var pageRequest = buildPageable(page, items);

        return service.findAll(pageRequest);
    }

    @PostMapping(value = "/ping-pong-tables")
    @ResponseStatus(HttpStatus.OK)
    public PingPongTableDto create(@Valid @RequestBody PingPongTableDto dto) {
        var pingPongTable = service.create(dto.toEntity(PingPongTable.class));

        return new PingPongTableDto().toDto(pingPongTable);
    }

    private Pageable buildPageable(Integer page, Integer items) {
        var currentPage = (page == null ? DEFAULT_PAGE : page);
        var itemsPerPage = (items == null ? DEFAULT_ITEMS_PER_PAGE : items);
        var offset = currentPage - 1;

        return PageRequest.of(offset, itemsPerPage, Sort.unsorted());
    }
}