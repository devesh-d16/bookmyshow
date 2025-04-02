package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.MovieDTO;
import com.devesh.bookmyshow.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.create(movieDTO),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PutMapping("/{title}")
    public ResponseEntity<?> updateMovie(@PathVariable String title, @RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.updateMovie(title, movieDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
