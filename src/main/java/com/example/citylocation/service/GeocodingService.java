package com.example.citylocation.service;


import com.example.citylocation.dto.GeocodingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class GeocodingService {
    private final RestClient restClient;

    public GeocodingService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .build();
    }

    public GeocodingResponse getLocation(String locationName) {

        GeocodingResponse[] response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", locationName)
                        .queryParam("format", "json")
                        .queryParam("limit", 1)
                        .build())
                .header("User-Agent", "city-location-app")
                .retrieve()
                .body(GeocodingResponse[].class);

        if (response == null || response.length == 0) {
            System.out.println("No location found :"+locationName);
            return null;
        }

        return response[0];

    }
}
