package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.Payment;
import com.devesh.bookmyshow.enums.PaymentStatus;
import com.devesh.bookmyshow.enums.PaymentType;
import com.devesh.bookmyshow.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment processPayment(double amount, PaymentType paymentType) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentType);
        payment.setPaymentStatus(PaymentStatus.PENDING);

        // Simulating payment gateway processing
        boolean isSuccess = Math.random() > 0.1; // 90% success rate simulation
        payment.setPaymentStatus(isSuccess ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);

        return paymentRepository.save(payment);
    }
}