package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.MovieDTO;
import com.devesh.bookmyshow.entity.Movie;
import com.devesh.bookmyshow.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie create(MovieDTO movieDTO) {
        
    }
}
