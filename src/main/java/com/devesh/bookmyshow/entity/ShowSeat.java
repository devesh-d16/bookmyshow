package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "show_seat")
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(nullable = false)
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus seatStatus;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Version
    private Long version; // For optimistic locking
}


