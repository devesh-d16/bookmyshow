package com.devesh.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "city_detail")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(nullable = false, unique = true)
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Theater> theaters;
}
