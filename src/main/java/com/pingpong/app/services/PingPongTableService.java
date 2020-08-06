package com.pingpong.app.services;

import com.pingpong.app.entities.PingPongTable;
import com.pingpong.app.repositories.PingPongTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PingPongTableService {

    private final PingPongTableRepository repository;

    public Page<PingPongTable> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public PingPongTable create(PingPongTable pingPongTable) {
        return repository.save(pingPongTable);
    }
}
