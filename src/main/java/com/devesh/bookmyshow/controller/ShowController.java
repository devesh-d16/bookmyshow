package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.ShowDTO;
import com.devesh.bookmyshow.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<?> createShow(@RequestBody ShowDTO showDTO){
        return new ResponseEntity<>(showService.createShow(showDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{showId}")
    public ResponseEntity<?> getShowByID(@PathVariable Long showId){
        return new ResponseEntity<>(showService.getShowById(showId), HttpStatus.OK);
    }

}
