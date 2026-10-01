package com.example.citylocation.controller;



import com.example.citylocation.entity.City;
import com.example.citylocation.service.CityService;
import com.example.citylocation.service.GeocodingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class CityController {
    private final GeocodingService geocodingService;
    private CityService cityService;
    public CityController(CityService cityService, GeocodingService geocodingService) {
        this.cityService = cityService;
        this.geocodingService = geocodingService;
    }

    @GetMapping("/process-csv")
    public String processCsv() throws InterruptedException {

        String filePath = "C:\\office project\\city-location\\src\\main\\resources\\locations.csv";

        cityService.cvRead(filePath);

        return "processing data........";
    }
}
