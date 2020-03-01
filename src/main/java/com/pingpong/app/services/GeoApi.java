package com.pingpong.app.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.pingpong.app.models.Coordinates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class GeoApi {

    private final GeoApiContext apiContext;

    public GeoApi(@Value("${services.geocoding.api-key}") String apiKey) {
        apiContext = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    public GeocodingResult[] fetchAddressByCoordinates(Coordinates coordinates) {
        try {
            var latLng = new LatLng(coordinates.getLat(), coordinates.getLng());
            return GeocodingApi.reverseGeocode(apiContext, latLng).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "[GeoApi]", e);
        }
    }
}
