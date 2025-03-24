package com.devesh.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "ticket_detail")
@Entity
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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show; // FIXED: Removed redundant theater & screen references
}
