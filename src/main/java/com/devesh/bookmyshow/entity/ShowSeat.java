package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "show-seat")
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
    private Screen screen; // FIXED: Now linked to screen directly

    @Version
    private Long version;
}

