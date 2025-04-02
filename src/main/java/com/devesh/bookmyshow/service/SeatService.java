package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.ShowSeat;
import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.repository.ShowSeatRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final ShowSeatRepository showSeatRepository;

    @Transactional
    public ShowSeat lockSeat(Long seatId) {
        ShowSeat seat = showSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
            throw new RuntimeException("Seat " + seatId + " is not available.");
        }
        seat.setSeatStatus(SeatStatus.RESERVED);

        try {
            return showSeatRepository.save(seat);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Seat " + seatId + " was reserved by someone else.");
        }
    }

    @Transactional
    public void bookSeat(Long seatId) {
        ShowSeat seat = showSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getSeatStatus() != SeatStatus.RESERVED) {
            throw new RuntimeException("Seat " + seatId + " must be reserved before booking.");
        }

        seat.setSeatStatus(SeatStatus.BOOKED);

        try {
            showSeatRepository.save(seat);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Seat " + seatId + " was already booked by someone else.");
        }
    }
}
