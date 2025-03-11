package com.devesh.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Ticket {

    private Long ticketId;
    private LocalDateTime bookingTime;
    private List<Seat> seats;

    private Payment payment;
    private Theater theater;
    private Show show;
    private Screen screen;

}

