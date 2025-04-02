package com.devesh.bookmyshow.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenId;

    @Column(nullable = false)
    private String screenName;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows;

    @OneToOne(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    private ScreenSeat screenSeat;
}
