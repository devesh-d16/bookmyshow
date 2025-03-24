package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.TheaterDTO;
import com.devesh.bookmyshow.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping
    public ResponseEntity<?> createTheater(@RequestBody TheaterDTO theater){
        return new ResponseEntity<>(theaterService.create(theater), HttpStatus.CREATED);
    }

    @PutMapping("/{theaterName}")
    public ResponseEntity<?> updateTheater(@RequestBody TheaterDTO theater, @PathVariable String theaterName){
        return new ResponseEntity<>(theaterService.updateTheater(theater, theaterName),HttpStatus.CREATED);
    }

    @DeleteMapping("/{theaterName}")
    public ResponseEntity<?> deleteTheater(@PathVariable String theaterName){
        theaterService.deleteTheater(theaterName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{theaterName}/screens")
    public ResponseEntity<?> findAllScreenByTheater(@PathVariable String theaterName){
        return new ResponseEntity<>(theaterService.getAllScreenByTheater(theaterName), HttpStatus.OK);
    }
}
