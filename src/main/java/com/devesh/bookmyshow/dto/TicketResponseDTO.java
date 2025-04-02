package com.devesh.bookmyshow.dto;


import com.devesh.bookmyshow.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class TicketResponseDTO {
    private Long ticketId;
    private Long userId;
    private String movieTitle;
    private LocalDateTime showTime;
    private List<Integer> seatNumbers;  // List of selected seat numbers
    private double totalAmount;
    private PaymentStatus paymentStatus;  // E.g., SUCCESS, FAILED

}
