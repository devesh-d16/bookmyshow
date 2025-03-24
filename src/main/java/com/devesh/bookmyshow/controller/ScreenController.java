package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.ScreenDTO;
import com.devesh.bookmyshow.dto.TheaterDTO;
import com.devesh.bookmyshow.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PostMapping
    public ResponseEntity<?> addScreenToTheater(@RequestBody ScreenDTO screenDTO){
        return new ResponseEntity<>(screenService.createScreen(screenDTO), HttpStatus.CREATED);
    }
}