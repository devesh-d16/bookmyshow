package com.devesh.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class Show {

    private Long showId;

    private Movie movie;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<Seat> seats;
    private int availableSeats;

    private double price;

}
