package com.pingpong.app.controllers;

import com.pingpong.app.dtos.PingPongTableDto;
import com.pingpong.app.entities.PingPongTable;
import com.pingpong.app.services.PingPongTableService;
import com.pingpong.app.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PingPongTableController {

    private final PingPongTableService service;

    @GetMapping(value = "/ping-pong-tables")
    @ResponseStatus(HttpStatus.OK)
    public Page<PingPongTable> findAll(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "items", required = false) Integer items) {

        var pageRequest = PageUtils.buildPageRequest(page, items);
        return service.findAll(pageRequest);
    }

    @PostMapping(value = "/ping-pong-tables")
    @ResponseStatus(HttpStatus.OK)
    public PingPongTableDto create(@Valid @RequestBody PingPongTableDto dto) {

        var pingPongTable = service.create(dto.to(PingPongTable.class));
        return new PingPongTableDto().from(pingPongTable);
    }
}