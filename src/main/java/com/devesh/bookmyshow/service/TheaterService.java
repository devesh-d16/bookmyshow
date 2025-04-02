package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.TheaterDTO;
import com.devesh.bookmyshow.entity.City;
import com.devesh.bookmyshow.entity.Screen;
import com.devesh.bookmyshow.entity.Theater;
import com.devesh.bookmyshow.exceptions.ResourceNotFoundException;
import com.devesh.bookmyshow.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final CityService cityService;
    private final TheaterRepository theaterRepository;

    public Theater createTheater(TheaterDTO theaterRequest) {
        City city = cityService.findCity(theaterRequest.getCityName());
        if (city == null) {
            throw new ResourceNotFoundException("City not found: " + theaterRequest.getCityName());
        }

        Theater theater = new Theater();
        theater.setTheaterName(theaterRequest.getTheaterName());
        theater.setCity(city);

        city.getTheaters().add(theater);

        return theaterRepository.save(theater);
    }

    @Transactional
    public void deleteTheater(Long theaterId) {
        theaterRepository.deleteByTheaterId(theaterId);
    }

    public Theater updateTheater(TheaterDTO theaterDTO, Long theaterId) {
        Theater theater = theaterRepository.findTheaterByTheaterId(theaterId);
        if (theater == null) {
            throw new IllegalArgumentException("Theater not found with ID " + theaterId);
        }

        City city = cityService.findCity(theaterDTO.getCityName());
        if (city == null) {
            throw new ResourceNotFoundException("City not found: " + theaterDTO.getCityName());
        }
        theater.setCity(city);
        theater.setTheaterName(theaterDTO.getTheaterName());

        return theaterRepository.save(theater);
    }

    public Theater findTheaterById(Long theaterId) {
        return Optional.ofNullable(theaterRepository.getTheaterByTheaterId(theaterId))
                .orElseThrow(() -> new IllegalArgumentException("Theater with ID " + theaterId + " not found."));
    }

    public List<Screen> getAllScreenByTheater(Long theaterId) {
        Theater theater = theaterRepository.findTheaterByTheaterId(theaterId);
        if (theater == null) {
            throw new ResourceNotFoundException("Theater not found with ID " + theaterId);
        }
        return theater.getScreens();
    }
}
