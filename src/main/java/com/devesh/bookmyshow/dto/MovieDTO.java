package com.devesh.bookmyshow.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class MovieDTO {
    private String title;
    private String genre;
    private Long duration;
    private String language;
    private String description;
    private Double rating;
    private Date releaseDate;
}
