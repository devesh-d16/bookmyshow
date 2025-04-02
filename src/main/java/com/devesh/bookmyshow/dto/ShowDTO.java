package com.devesh.bookmyshow.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ShowDTO {
    private Long movieId;
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endingTime;
}

