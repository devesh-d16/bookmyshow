package com.devesh.bookmyshow.service;


import com.devesh.bookmyshow.entity.MovieDocument;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.devesh.bookmyshow.repository.MovieSearchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSearchService {

    private final MovieSearchRepository movieSearchRepository;

    public List<MovieDocument> searchByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidRequestException("Title cannot be blank.");
        }
        return movieSearchRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<MovieDocument> searchByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new InvalidRequestException("Genre cannot be blank.");
        }
        return movieSearchRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<MovieDocument> searchByLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new InvalidRequestException("Language cannot be blank.");
        }
        return movieSearchRepository.findByLanguageContainingIgnoreCase(language);
    }
}
