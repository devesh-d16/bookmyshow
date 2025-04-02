package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.service.MovieSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies/search")
@RequiredArgsConstructor
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    @GetMapping("/title")
    public ResponseEntity<?> searchByTitle(@RequestParam String query) {
        return ResponseEntity.ok(movieSearchService.searchByTitle(query));
    }

    @GetMapping("/genre")
    public ResponseEntity<?> searchByGenre(@RequestParam String query) {
        return ResponseEntity.ok(movieSearchService.searchByGenre(query));
    }

    @GetMapping("/language")
    public ResponseEntity<?> searchByLanguage(@RequestParam String query) {
        return ResponseEntity.ok(movieSearchService.searchByLanguage(query));
    }
}

