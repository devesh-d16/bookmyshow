package com.devesh.bookmyshow.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ScreenSeatDTO {
    private int totalRows;
    private int totalCols;
    private List<ScreenSeatTypeDTO> seatTypes;
}
