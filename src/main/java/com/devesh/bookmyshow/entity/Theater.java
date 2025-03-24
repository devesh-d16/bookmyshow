package com.devesh.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "theater_detail")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;

    @Column(nullable = false)
    private String theaterName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Screen> screens;
}
