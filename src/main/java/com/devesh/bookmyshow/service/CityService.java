package com.devesh.bookmyshow.service;


import com.devesh.bookmyshow.dto.CityRequestDTO;
import com.devesh.bookmyshow.entity.City;
import com.devesh.bookmyshow.entity.Theater;
import com.devesh.bookmyshow.exceptions.DuplicateResourceFoundException;
import com.devesh.bookmyshow.exceptions.ResourceNotFoundException;
import com.devesh.bookmyshow.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public City saveCity(CityRequestDTO city) {
        if (findCity(city.getCityName()) != null) {
            throw new DuplicateResourceFoundException("City already exists");
        }
        City newCity = new City();
        newCity.setCityName(city.getCityName());
        return cityRepository.save(newCity);
    }

    public City findCity(String cityName) {
        return cityRepository.getCityByCityName(cityName);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City updateCity(String cityName, CityRequestDTO updatedCity) {
        City city = findCity(cityName);
        if (city == null) {
            throw new ResourceNotFoundException("City not found: " + cityName);
        }
        city.setCityName(updatedCity.getCityName());
        return cityRepository.save(city);
    }

    @Transactional
    public void deleteCity(String cityName) {
        cityRepository.deleteByCityName(cityName);
    }

    public List<Theater> findTheaterByCity(String cityName) {
        City city = findCity(cityName);
        if (city == null) {
            throw new ResourceNotFoundException("City not found: " + cityName);
        }
        return city.getTheaters();
    }
}
