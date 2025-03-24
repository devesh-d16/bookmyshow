package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.ShowSeat;
import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.repository.ShowSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final ShowSeatRepository showSeatRepository;

    // Check if a seat is available
    public boolean isSeatAvailable(Long seatId) {
        ShowSeat seat = showSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        return seat.getSeatStatus() == SeatStatus.AVAILABLE;
    }

    // Lock a seat (during booking)
    public void lockSeat(Long seatId) {
        ShowSeat seat = showSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setSeatStatus(SeatStatus.RESERVED);
        showSeatRepository.save(seat);
    }

    // Mark a seat as booked
    public void bookSeat(Long seatId) {
        ShowSeat seat = showSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setSeatStatus(SeatStatus.BOOKED);
        showSeatRepository.save(seat);
    }
}
