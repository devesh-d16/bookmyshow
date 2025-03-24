package com.devesh.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie_detail")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    private String genre;

    @Column(nullable = false)
    private Long duration;

    private String language;
    private String description;
    private Double rating;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows;
}

