package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.entity.City;

import com.devesh.bookmyshow.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody City newCity){
        return new ResponseEntity<>(cityService.saveCity(newCity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCities(){
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<?> findCity(@PathVariable String cityName){
        return new ResponseEntity<>(cityService.findCity(cityName), HttpStatus.OK);
    }

    @PutMapping("/{cityName}")
    public ResponseEntity<?> updateCity(@PathVariable String cityName, @RequestBody City updatedCity){
        return new ResponseEntity<>(cityService.updateCity(cityName, updatedCity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cityName}")
    public ResponseEntity<?> deleteCity(@PathVariable String cityName){
        cityService.deleteCity(cityName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{cityName}/theaters")
    public ResponseEntity<?> findAllTheaterByCity(@PathVariable String cityName){
        return new ResponseEntity<>(cityService.findTheaterByCity(cityName), HttpStatus.OK);
    }
}
