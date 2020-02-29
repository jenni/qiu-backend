package com.pingpong.app.repositories;

import com.pingpong.app.entities.PingPongTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingPongTableRepository extends JpaRepository<PingPongTable, Integer> {

    Page<PingPongTable> findAll(Pageable pageRequest);
}
