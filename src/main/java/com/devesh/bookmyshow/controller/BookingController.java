package com.devesh.bookmyshow.controller;


import com.devesh.bookmyshow.dto.BookingRequestDTO;
import com.devesh.bookmyshow.dto.TicketResponseDTO;
import com.devesh.bookmyshow.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponseDTO> bookTicket(@RequestBody BookingRequestDTO bookingRequest) {
        TicketResponseDTO ticketResponse = bookingService.bookTicket(bookingRequest);
        return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
    }
}
