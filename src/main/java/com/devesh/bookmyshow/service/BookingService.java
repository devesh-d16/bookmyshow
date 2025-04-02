package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.BookingRequestDTO;
import com.devesh.bookmyshow.dto.TicketResponseDTO;
import com.devesh.bookmyshow.entity.*;
import com.devesh.bookmyshow.enums.*;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.exceptions.ResourceNotFoundException;
import com.devesh.bookmyshow.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserService userService;
    private final CityService cityService;
    private final MovieService movieService;
    private final TheaterService theaterService;
    private final ShowService showService;
    private final SeatService seatService;
    private final PaymentService paymentService;
    private final TicketRepository ticketRepository;

    @Transactional
    public TicketResponseDTO bookTicket(BookingRequestDTO bookingRequest) {
        // 1. Basic entity lookups
        User user = userService.getUserById(bookingRequest.getUserId());
        City city = cityService.findCity(bookingRequest.getCityName());
        Movie movie = movieService.findMovieByTitle(bookingRequest.getMovieTitle());
        Theater theater = theaterService.findTheaterById(bookingRequest.getTheaterId());
        Show show = showService.getShowById(bookingRequest.getShowId());

        // 2. Validate city-theater & show-movie
        if (!theater.getCity().equals(city)) {
            throw new InvalidRequestException("The selected theater is not in the correct city.");
        }
        if (!show.getMovie().equals(movie)) {
            throw new InvalidRequestException("The selected movie does not match the show.");
        }

        // 3. Validate seats
        List<ShowSeat> selectedSeats = new ArrayList<>();
        for (Long seatId : bookingRequest.getSeatIds()) {
            ShowSeat seat = seatService.lockSeat(seatId);

            // Check seat belongs to the same show
            if (!seat.getShow().equals(show)) {
                throw new InvalidRequestException("Seat " + seatId + " is not part of the selected show.");
            }
            selectedSeats.add(seat);
        }

        // 4. Calculate total amount
        double totalAmount = selectedSeats.stream().mapToDouble(ShowSeat::getPrice).sum();
        if (totalAmount <= 0) {
            throw new InvalidRequestException("Invalid total amount calculation.");
        }

        // 5. Create Ticket (PENDING)
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setShowSeats(selectedSeats);
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setBookingTime(LocalDateTime.now());
        ticketRepository.save(ticket);

        // 6. Process Payment
        Payment payment = paymentService.processPayment(ticket, totalAmount, PaymentType.valueOf(bookingRequest.getPaymentType()));
        if (payment.getPaymentStatus() == PaymentStatus.FAILED) {
            throw new ResourceNotFoundException("Payment failed! Booking cannot be completed.");
        }

        // Link Payment & Confirm Ticket
        payment.setTicket(ticket);
        ticket.setPayment(payment);
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticketRepository.save(ticket);

        // 7. Mark seats as booked
        selectedSeats.forEach(seat -> seatService.bookSeat(seat.getSeatId()));

        // 8. Return Response
        return new TicketResponseDTO(
                ticket.getTicketId(),
                user.getUserId(),
                movie.getTitle(),
                show.getStartTime(),
                selectedSeats.stream().map(ShowSeat::getSeatNumber).collect(Collectors.toList()),
                totalAmount,
                payment.getPaymentStatus()
        );
    }
}
