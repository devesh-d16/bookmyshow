package com.devesh.bookmyshow.dto;

import com.devesh.bookmyshow.enums.SeatType;
import lombok.Getter;

@Getter
public class ScreenSeatTypeDTO {
    private SeatType seatType;
    private int seatCount;
    private double basePrice;
}
