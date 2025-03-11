package com.devesh.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Movie {

    private Long movieId;

    private String title;
    private String genre;
    private Long duration;
    private String language;
    private String description;
    private Double rating;
    private LocalDateTime releaseDate;

    private Show show;
}
