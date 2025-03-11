package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Payment {

    private Long paymentId;
    private Ticket ticket;
    private Double amount;
    private PaymentType paymentMethod;
    private PaymentStatus paymentStatus;

}
