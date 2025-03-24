package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ScreenSeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "screen_seat_id", nullable = false)
    @JsonIgnore
    private ScreenSeat screenSeat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Column(nullable = false)
    private int seatCount;

    @Column(nullable = false)
    private double basePrice;
}
