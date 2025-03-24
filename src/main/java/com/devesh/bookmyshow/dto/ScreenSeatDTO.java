package com.devesh.bookmyshow.dto;


import lombok.Getter;

import java.util.List;

@Getter
public class ScreenSeatDTO {
    private int totalRows;
    private int totalCols;
    private List<ScreenSeatTypeDTO> seatTypes;
}
