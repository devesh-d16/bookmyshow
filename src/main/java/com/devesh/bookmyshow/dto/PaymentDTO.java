package com.devesh.bookmyshow.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private String paymentType;  // E.g., CREDIT_CARD, DEBIT_CARD, etc.
    private double amount;
    private String paymentStatus;  // E.g., SUCCESS, FAILED
}
