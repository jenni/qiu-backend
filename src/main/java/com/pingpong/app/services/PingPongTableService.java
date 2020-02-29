package com.pingpong.app.services;

import com.google.maps.errors.ApiException;
import com.pingpong.app.entities.PingPongTable;
import com.pingpong.app.repositories.PingPongTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PingPongTableService {

    private final PingPongTableRepository repository;
    private final GeoApi geoApi;

    public Page<PingPongTable> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PingPongTable create(PingPongTable pingPongTable) throws InterruptedException, ApiException, IOException {

        var geocodingResults = geoApi.fetchAddressByCoordinates(pingPongTable.getCoordinates());

        pingPongTable.setCreatedBy(1);
        pingPongTable.setGeoApi(geocodingResults);

        return repository.save(pingPongTable);
    }

}
