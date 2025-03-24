package com.devesh.bookmyshow.dto;


import com.devesh.bookmyshow.entity.ShowSeat;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
public class BookTicketRequestDTO {

    private String username;
    private Long showId;

    private String seatType;
    private List<Long> seatIds;
}
