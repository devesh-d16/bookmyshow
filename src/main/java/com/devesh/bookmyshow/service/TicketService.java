package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.TicketDTO;
import com.devesh.bookmyshow.entity.ShowSeat;
import com.devesh.bookmyshow.entity.Ticket;
import com.devesh.bookmyshow.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public List<TicketDTO> getTicketsByUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findAllByUser_UserId(userId);

        return tickets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setTicketId(ticket.getTicketId());
        dto.setUserId(ticket.getUser().getUserId());
        dto.setShowId(ticket.getShow().getShowId());
        dto.setMovieTitle(ticket.getShow().getMovie().getTitle());
        dto.setTheaterName(ticket.getShow().getScreen().getTheater().getTheaterName());
        dto.setSeatIds(ticket.getShowSeats().stream()
                .map(ShowSeat::getSeatId)
                .collect(Collectors.toList()));
        dto.setTotalAmount(ticket.getShowSeats().stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum());
        dto.setTicketStatus(ticket.getTicketStatus().name());
        return dto;
    }
}
