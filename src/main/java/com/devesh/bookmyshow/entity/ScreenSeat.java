package com.devesh.bookmyshow.entity;


import com.devesh.bookmyshow.enums.SeatType;
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
    private Screen screen;

    @ElementCollection
    @CollectionTable(name = "screen_seat_details", joinColumns = @JoinColumn(name = "seat_id"))
    @MapKeyColumn(name = "seat_type")
    @Column(name = "seat_count")
    private Map<SeatType, Integer> seatCountByType;

    @ElementCollection
    @CollectionTable(name = "seat_pricing", joinColumns = @JoinColumn(name = "seat_id"))
    @MapKeyColumn(name = "seat_type")
    @Column(name = "base_price")
    private Map<SeatType, Double> seatPricingByType;

}
