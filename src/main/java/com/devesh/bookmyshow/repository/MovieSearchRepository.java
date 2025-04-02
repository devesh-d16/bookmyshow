package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.MovieDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieSearchRepository extends ElasticsearchRepository<MovieDocument, String> {

    List<MovieDocument> findByTitleContainingIgnoreCase(String title);

    List<MovieDocument> findByGenreContainingIgnoreCase(String genre);

    List<MovieDocument> findByLanguageContainingIgnoreCase(String language);
}

