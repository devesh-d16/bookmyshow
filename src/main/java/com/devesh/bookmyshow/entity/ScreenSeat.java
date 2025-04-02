package com.devesh.bookmyshow.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "screen_seat")
public class ScreenSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(nullable = false)
    private int totalRows;

    @Column(nullable = false)
    private int totalCols;

    @OneToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @OneToMany(mappedBy = "screenSeat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreenSeatType> seatTypes;
}

