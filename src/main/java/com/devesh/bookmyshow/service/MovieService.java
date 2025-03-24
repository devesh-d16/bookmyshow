package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.MovieDTO;
import com.devesh.bookmyshow.entity.Movie;
import com.devesh.bookmyshow.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public Movie create(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }

    public Movie updateMovie(String title, MovieDTO movieDTO) {
        Movie movie = getMovieByTitle(title);
        movie = modelMapper.map(movieDTO, Movie.class);
        return movieRepository.save(movie);
    }

    public void deleteMovie(String title){
        Movie movie = getMovieByTitle(title);
        movieRepository.delete(movie);
    }
}
