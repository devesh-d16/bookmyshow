package com.devesh.bookmyshow.dto;


import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
public class ScreenDTO {

    private String screenName;
    private Long theaterId;
    private List<ScreenSeatDTO> screenSeats;

}
