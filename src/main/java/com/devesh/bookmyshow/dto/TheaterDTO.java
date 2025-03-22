package com.devesh.bookmyshow.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class TheaterDTO {

    private String theaterName;
    private String cityName;

}
