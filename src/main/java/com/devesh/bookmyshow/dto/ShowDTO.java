package com.devesh.bookmyshow.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO {
    private Long movieId;
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endingTime;
}

