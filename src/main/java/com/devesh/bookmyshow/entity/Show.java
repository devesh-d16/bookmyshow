package com.devesh.bookmyshow.entity;

import com.devesh.bookmyshow.enums.ShowTimingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "show_detail")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endingTime;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowSeat> showSeats;
}

