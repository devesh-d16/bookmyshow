package com.devesh.bookmyshow.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class BookTicketRequestDTO {

    private String username;
    private Long showId;
    private String seatType;
    private List<Long> seatIds;
}
