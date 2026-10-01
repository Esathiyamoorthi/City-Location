package com.example.citylocation.service;


import com.example.citylocation.dto.GeocodingResponse;
import com.example.citylocation.entity.City;
import com.example.citylocation.repository.CityRepository;
import com.example.citylocation.util.CsvReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService  {
  private final CityRepository cityRepository;
  private final GeocodingService geocodingService;
  public CityService(CityRepository cityRepository, GeocodingService geocodingService) {
      this.cityRepository = cityRepository;
      this.geocodingService = geocodingService;
  }
     public void cvRead(String filePath) throws InterruptedException {
      CsvReader csvReader = new CsvReader();
         List<String> nm=csvReader.readCsv(filePath);
         int total = nm.size();
         int success = 0;
         int failed = 0;
         System.out.println("Total locations: " + total);

         for (int i = 0; i < nm.size(); i++) {

             String location =nm.get(i);

             System.out.println("\nProcessing " + (i + 1) + "/" + total + ": " + location);

             try {

                 City savedCity = save(location);

                 if (savedCity != null) {
                     success++;
                 } else {
                     failed++;
                 }

                 // Wait 1 second before next API request
                 Thread.sleep(1000);

             } catch (Exception e) {

                 failed++;

                 System.out.println("Error processing: " + location);
             }
         }

         System.out.println("\n==============================");
         System.out.println("CSV PROCESSING COMPLETED");
         System.out.println("==============================");
         System.out.println("Total locations : " + total);
         System.out.println("Successfully saved: " + success);
         System.out.println("Failed / Not found: " + failed);
         System.out.println("==============================");


     }
    public City save(String locationName) {
        locationName = locationName.replace("\"", "").trim();

        // Check if location already exists
        java.util.Optional<City> existingCity = cityRepository.findByLocationName(locationName);

        if (existingCity.isPresent()) {

            System.out.println("Already exists: " + locationName);

            return existingCity.get();
        }

        System.out.println("Sending to Geocoding API: [" + locationName + "]");

        try {

            // Call Geocoding API
            GeocodingResponse response = geocodingService.getLocation(locationName);

            if (response == null) {

                System.out.println(" Location not found: " + locationName);

                return null;
            }

            Double latitude = Double.parseDouble(response.getLat());

            Double longitude = Double.parseDouble(response.getLon());

            City city = new City(locationName, latitude, longitude);

            City savedCity = cityRepository.save(city);

            System.out.println(" Saved: " + savedCity.getLocationName() + " | Latitude: " + savedCity.getLatitude() + " | Longitude: " + savedCity.getLongitude());

            return savedCity;

        } catch (Exception e) {

            System.out.println(" Error processing: " + locationName);

            e.printStackTrace();

            return null;
        }
    }

}
