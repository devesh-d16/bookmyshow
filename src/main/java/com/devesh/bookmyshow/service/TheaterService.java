package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.TheaterDTO;
import com.devesh.bookmyshow.entity.City;
import com.devesh.bookmyshow.entity.Theater;
import com.devesh.bookmyshow.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final CityService cityService;
    private final TheaterRepository theaterRepository;

    public Theater create(TheaterDTO theaterRequest) {
        City city = cityService.findCity(theaterRequest.getCityName());

        Theater theater = new Theater();
        theater.setTheaterName(theaterRequest.getTheaterName());
        theater.setCity(city);

        city.getTheaters().add(theater);
        cityService.saveCity(city);

        return theaterRepository.save(theater);
    }

    public void deleteTheater(String theaterName) {
        theaterRepository.deleteByTheaterName(theaterName);
    }

    public Theater updateTheater(TheaterDTO theater, String theaterName) {
        Theater theaterUpdate = theaterRepository.getTheaterByTheaterName(theaterName);
        City city = cityService.findCity(theaterUpdate.getTheaterName());
        theaterUpdate.setCity(city);
        theaterUpdate.setTheaterName(theater.getTheaterName());
        return theaterRepository.save(theaterUpdate);
    }
}
