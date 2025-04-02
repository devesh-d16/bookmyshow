package com.devesh.bookmyshow.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketDTO {
    private Long ticketId;
    private Long userId;
    private Long showId;
    private String movieTitle;
    private String theaterName;
    private List<Long> seatIds;
    private double totalAmount;
    private String ticketStatus;
}
