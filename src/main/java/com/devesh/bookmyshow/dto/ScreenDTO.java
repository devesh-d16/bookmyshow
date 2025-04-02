package com.devesh.bookmyshow.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScreenDTO {
    private String screenName;
    private Long theaterId;
    private List<ScreenSeatDTO> screenSeats;
}
