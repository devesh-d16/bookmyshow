package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.Ticket;
import com.devesh.bookmyshow.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    // Fetch ticket by ID
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

//    // Fetch all tickets for a user
//    public List<Ticket> getTicketsByUser(Long userId) {
//        return ticketRepository.findByUserId(userId);
//    }
//
//    // Cancel a ticket
//    public void cancelTicket(Long ticketId) {
//        Ticket ticket = ticketRepository.findById(ticketId)
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//        ticket.setStatus(TicketStatus.CANCELLED);
//        ticketRepository.save(ticket);
//    }
}
