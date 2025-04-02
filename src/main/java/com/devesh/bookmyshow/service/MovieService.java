package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.MovieDTO;
import com.devesh.bookmyshow.entity.Movie;
import com.devesh.bookmyshow.entity.MovieDocument;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.exceptions.ResourceNotFoundException;
import com.devesh.bookmyshow.persistence.MoviePersistence;
import com.devesh.bookmyshow.repository.MovieRepository;
import com.devesh.bookmyshow.repository.MovieSearchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final ModelMapper modelMapper;
    private final MoviePersistence moviePersistence; // Dual store (SQL + ES)

    public Movie create(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);

        if (movie.getRating() < 0.0 || movie.getRating() > 10.0) {
            throw new InvalidRequestException("Rating must be between 0.0 and 10.0");
        }
        if (movie.getReleaseDate() == null) {
            throw new InvalidRequestException("Release date required");
        }

        moviePersistence.save(movie);
        return movie;
    }

    public List<Movie> getAllMovies() {
        return moviePersistence.findAll();
    }

    public Movie updateMovie(String title, MovieDTO movieDTO) {
        Movie movie = moviePersistence.findByTitle(title);
        if (movie == null) {
            throw new ResourceNotFoundException("No movie found with title " + title);
        }
        modelMapper.map(movieDTO, movie);
        return moviePersistence.save(movie);
    }

    public void deleteMovie(Long id) {
        moviePersistence.deleteById(id);
    }

    public Movie findMovieByTitle(String title) {
        Movie movie = moviePersistence.findByTitle(title);
        if (movie == null) {
            throw new ResourceNotFoundException("No movie found with title " + title);
        }
        return movie;
    }
}
