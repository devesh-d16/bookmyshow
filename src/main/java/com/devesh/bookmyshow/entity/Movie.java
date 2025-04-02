package com.devesh.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Long duration;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private LocalDate releaseDate; // changed to LocalDate

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows;
}
