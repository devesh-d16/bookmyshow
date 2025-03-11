package com.devesh.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Theater {

    private Long theaterId;
    private String name;

    private City city;
    private List<Screen> screens;
}
