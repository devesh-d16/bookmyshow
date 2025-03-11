package com.devesh.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class City {

    private Long cityId;
    private String cityName;
    
    private List<Theater> theaters;
}
