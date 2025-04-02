package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @ManyToMany
    @JoinTable(
            name = "ticket_show_seat",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "show_seat_id")
    )
    private List<ShowSeat> showSeats;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
}
