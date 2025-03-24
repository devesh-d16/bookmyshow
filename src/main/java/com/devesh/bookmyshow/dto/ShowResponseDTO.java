package com.devesh.bookmyshow.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponseDTO {
    private Long showId;
    private String movieName;
    private String screenName;
    private LocalDateTime startTime;
    private LocalDateTime endingTime;
    private List<ShowSeatDTO> showSeats;
}
