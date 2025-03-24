package com.devesh.bookmyshow.service;


import com.devesh.bookmyshow.entity.City;
import com.devesh.bookmyshow.entity.Theater;
import com.devesh.bookmyshow.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public City saveCity(City newCity) {
        return cityRepository.save(newCity);
    }

    public City findCity(String cityName) {
        return cityRepository.getCityByCityName(cityName);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City updateCity(String cityName, City updatedCity) {
        City city = findCity(cityName);
        city.setCityName(updatedCity.getCityName());
        return cityRepository.save(city);
    }

    public void deleteCity(String cityName) {
        cityRepository.deleteCityByCityName(cityName);
    }

    public List<Theater> findTheaterByCity(String cityName) {
        return findCity(cityName).getTheaters();
    }
}
