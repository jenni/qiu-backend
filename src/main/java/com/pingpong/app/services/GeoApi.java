package com.pingpong.app.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.pingpong.app.models.Coordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class GeoApi {

    private final GeoApiContext API_CONTEXT;

    public GeoApi(@Value("${services.geocoding.api-key}") String apiKey) {
        API_CONTEXT = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    public GeocodingResult[] fetchAddressByCoordinates(Coordinates coordinates)
            throws InterruptedException, ApiException, IOException
    {
        var latLng = new LatLng(coordinates.getLat(), coordinates.getLng());

        return GeocodingApi.reverseGeocode(API_CONTEXT, latLng).await();
    }
}
