package com.pingpong.app.repositories;

import com.pingpong.app.entities.PingPongTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PingPongTableRepository extends JpaRepository<PingPongTable, Integer> {

    Page<PingPongTable> findAll(Pageable pageRequest);

    @Query(value = "select external_id from ping_pong_tables; ", nativeQuery = true)
    List<Integer> findAllExternalIds();
}
