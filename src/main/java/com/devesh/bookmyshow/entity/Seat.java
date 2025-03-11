package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.SeatType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Seat {

    private Long seatId;
    private int seatNumber;
    private SeatType seatType;
    private Boolean booked;

    private Show show;
}
