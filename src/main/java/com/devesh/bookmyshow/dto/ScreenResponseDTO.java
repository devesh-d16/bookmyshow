package com.devesh.bookmyshow.dto;

import lombok.Data;

@Data
public class ScreenResponseDTO {
    private Long screenId;
    private String screenName;
    private TheaterDTO theaterDetail;
    private ScreenSeatDTO seatDetails;
}
