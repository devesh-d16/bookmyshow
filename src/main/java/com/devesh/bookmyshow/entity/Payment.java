package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.PaymentStatus;
import com.devesh.bookmyshow.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "payment_detail")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
}

