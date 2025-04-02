package com.devesh.bookmyshow.dto;

import com.devesh.bookmyshow.enums.SeatType;
import lombok.Builder;
import lombok.Data;

@Data
public class ScreenSeatTypeDTO {
    private SeatType seatType;
    private int seatCount;
    private double basePrice;
}
