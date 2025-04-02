package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.TheaterDTO;
import com.devesh.bookmyshow.service.TheaterService;
import lombok.*;
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
        return new ResponseEntity<>(theaterService.createTheater(theater), HttpStatus.CREATED);
    }

    @PutMapping("/{theaterId}")
    public ResponseEntity<?> updateTheater(@RequestBody TheaterDTO theater, @PathVariable Long theaterId){
        return new ResponseEntity<>(theaterService.updateTheater(theater, theaterId),HttpStatus.OK);
    }

    @DeleteMapping("/{theaterId}")
    public ResponseEntity<?> deleteTheater(@PathVariable Long theaterId){
        theaterService.deleteTheater(theaterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{theaterId}/screens")
    public ResponseEntity<?> findAllScreenByTheater(@PathVariable Long theaterId){
        return new ResponseEntity<>(theaterService.getAllScreenByTheater(theaterId), HttpStatus.OK);
    }
}
