package com.devesh.bookmyshow.controller;

import com.devesh.bookmyshow.dto.TicketDTO;
import com.devesh.bookmyshow.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketDTO>> getAllTicketsByUser(@PathVariable Long userId) {
        List<TicketDTO> userTickets = ticketService.getTicketsByUser(userId);
        return ResponseEntity.ok(userTickets);
    }
}
