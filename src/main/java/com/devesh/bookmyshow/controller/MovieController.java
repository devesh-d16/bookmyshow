package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.MovieDTO;
import com.devesh.bookmyshow.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.create(movieDTO),HttpStatus.CREATED);
    }
}
