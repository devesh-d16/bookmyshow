package com.devesh.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Screen {

    private Long screenId;
    private Long seatingCapacity;

    private List<Show> shows;
}
