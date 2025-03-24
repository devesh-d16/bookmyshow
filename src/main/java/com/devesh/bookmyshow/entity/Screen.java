package com.devesh.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "screen_detail")
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
    @JsonIgnore
    private List<Show> shows;

    @OneToOne(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    private ScreenSeat screenSeat;
}
