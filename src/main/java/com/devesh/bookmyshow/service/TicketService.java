package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.Ticket;
import com.devesh.bookmyshow.enums.TicketStatus;
import com.devesh.bookmyshow.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public List<Ticket> getTicketsByUser(Long userId) {
        return ticketRepository.findAllByUser_UserId(userId);
    }

    public void cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Possibly check if show has already started or ended
        ticket.setTicketStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);
    }
}
