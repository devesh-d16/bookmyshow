package com.devesh.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ticket_detail")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    private Theater theater;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Show show;

}
