package com.devesh.bookmyshow.persistence;

import com.devesh.bookmyshow.entity.Movie;
import com.devesh.bookmyshow.entity.MovieDocument;
import com.devesh.bookmyshow.repository.MovieRepository;
import com.devesh.bookmyshow.repository.MovieSearchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoviePersistence implements IPersistence<Movie> {

    private final MovieRepository movieRepository;
    private final MovieSearchRepository movieSearchRepository;
    private final ModelMapper modelMapper;

    @Override
    public Movie save(Movie movie) {
        Movie saved = movieRepository.save(movie);

        MovieDocument movieDocument = modelMapper.map(saved, MovieDocument.class);
        movieDocument.setId(saved.getMovieId().toString());

        movieSearchRepository.save(movieDocument);
        return saved;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
        movieSearchRepository.deleteById(id.toString());
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }


    public Movie findByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }
}
