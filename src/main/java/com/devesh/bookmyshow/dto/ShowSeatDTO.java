package com.devesh.bookmyshow.dto;

import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.enums.SeatType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDTO {
    private Long seatId;
    private int seatNumber;
    private SeatType seatType;
    private SeatStatus seatStatus;
    private double price;
}
