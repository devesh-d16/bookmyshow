package com.devesh.bookmyshow.dto;


import lombok.Data;

import java.util.List;

@Data
public class TicketDTO {
    private Long ticketId;
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
    private double totalAmount;
    private String ticketStatus;  // E.g., CONFIRMED, PENDING
}
