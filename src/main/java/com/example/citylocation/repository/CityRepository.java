package com.example.citylocation.repository;

import com.example.citylocation.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> findByLocationName(String locationName);
}
