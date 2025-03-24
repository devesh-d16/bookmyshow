package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.BookTicketRequestDTO;
import com.devesh.bookmyshow.entity.*;
import com.devesh.bookmyshow.enums.*;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.repository.ShowRepository;
import com.devesh.bookmyshow.repository.ShowSeatRepository;
import com.devesh.bookmyshow.repository.TicketRepository;
import com.devesh.bookmyshow.repository.UserRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final ShowRepository showRepository;
    private final UserService userService;
    private final ShowService showService;


//    @Transactional
//    public Ticket bookTickets(BookTicketRequestDTO bookTicketRequestDTO){
//
//        User user = userService.findUserByName(bookTicketRequestDTO.getUsername());
//        Show show = showService.findShowByShowId(bookTicketRequestDTO.getShowId());
//
//        SeatType seatType = SeatType.valueOf(bookTicketRequestDTO.getSeatType());
//        List<ShowSeat> selectedSeats = showSeatRepository.findAllById(bookTicketRequestDTO.getSeatIds());
//
//    }

//        @Transactional
//        public Ticket bookTickets(Long userId, Long showId, List<Long> seatIds, PaymentType paymentType) {
//            // Fetch user, show, and seats
//            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//            Show show = showRepository.findById(showId).orElseThrow(() -> new RuntimeException("Show not found"));
//
//            List<ShowSeat> selectedSeats = showSeatRepository.findAllById(seatIds);
//
//            // Ensure seats are available
//            for (ShowSeat seat : selectedSeats) {
//                if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
//                    throw new RuntimeException("Selected seat " + seat.getSeatNumber() + " is already booked.");
//                }
//            }
//
//            // Calculate total price based on seat type and timing
//            double totalPrice = 0.0;
//            for (ShowSeat seat : selectedSeats) {
//                ScreenSeat screenSeat = seat.getScreen().getScreenSeat();
//                ScreenSeatType seatType;
//
//                // Base price of the seat based on type
//                double basePrice = seatType.getBasePrice();
//
//                // Timing adjustment
//                double adjustedPrice = adjustPriceBasedOnTiming(show.getStartTime(), basePrice);
//                totalPrice += adjustedPrice;
//
//                // Mark the seat as booked
//                seat.setSeatStatus(SeatStatus.BOOKED);
//                showSeatRepository.save(seat);
//            }
//
//            // Create the ticket
//            Ticket ticket = new Ticket();
//            ticket.setBookingTime(LocalDateTime.now());
//            ticket.setUser(user);
//            ticket.setShow(show);
//            ticket.setSeats(selectedSeats);
//            ticket.setTotalPrice(totalPrice);
//
//            // Process payment
//            Payment payment = paymentService.processPayment(ticket, paymentType);
//            ticket.setPayment(payment);
//
//            // Save ticket and return
//            ticketRepository.save(ticket);
//            return ticket;
//        }


//
//        private double adjustPriceBasedOnTiming(LocalDateTime showStartTime, double basePrice) {
//            // Determine the show timing
//            ShowTimingType showTimingType = getShowTimingType(showStartTime);
//
//            switch (showTimingType) {
//                case MORNING:
//                    return basePrice * 0.80; // 20% discount for morning shows
//                case AFTERNOON:
//                    return basePrice; // Normal price for afternoon shows
//                case NIGHT:
//                    return basePrice * 1.20; // 20% surge for night shows
//                default:
//                    return basePrice;
//            }
//        }
//
//        private ShowTimingType getShowTimingType(LocalDateTime showStartTime) {
//            int hour = showStartTime.getHour();
//
//            if (hour >= 9 && hour < 12) {
//                return ShowTimingType.MORNING;
//            } else if (hour >= 12 && hour < 18) {
//                return ShowTimingType.AFTERNOON;
//            } else {
//                return ShowTimingType.NIGHT;
//            }
//        }

}
