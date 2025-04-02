package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.Payment;
import com.devesh.bookmyshow.entity.Ticket;
import com.devesh.bookmyshow.enums.PaymentStatus;
import com.devesh.bookmyshow.enums.PaymentType;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment processPayment(Ticket ticket, double amount, PaymentType paymentType) {
        if (amount <= 0) {
            throw new InvalidRequestException("Payment amount must be greater than 0");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setTicket(ticket);
        payment.setPaymentMethod(paymentType);

        payment.setPaymentStatus(Math.random() > 0.05 ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);

        return paymentRepository.save(payment);
    }
}
