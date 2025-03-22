package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City getCityByCityName(String cityName);

    void deleteCityByCityName(String cityName);
}
