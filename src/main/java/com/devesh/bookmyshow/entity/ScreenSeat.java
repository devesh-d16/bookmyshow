package com.devesh.bookmyshow.entity;


import com.devesh.bookmyshow.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
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
    @JsonIgnore
    private Screen screen;

    @OneToMany(mappedBy = "screenSeat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreenSeatType> seatTypes;
}
