package com.devesh.bookmyshow.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDTO {
    private Long userId;
    private String cityName;
    private String movieTitle;
    private Long theaterId;
    private Long showId;
    private List<Long> seatIds;
    private String paymentType;
}
