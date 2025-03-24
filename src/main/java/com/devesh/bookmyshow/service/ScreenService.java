package com.devesh.bookmyshow.service;


import com.devesh.bookmyshow.dto.ScreenDTO;
import com.devesh.bookmyshow.dto.ScreenSeatDTO;
import com.devesh.bookmyshow.dto.ScreenSeatTypeDTO;
import com.devesh.bookmyshow.entity.Screen;
import com.devesh.bookmyshow.entity.ScreenSeat;
import com.devesh.bookmyshow.entity.ScreenSeatType;
import com.devesh.bookmyshow.entity.Theater;
import com.devesh.bookmyshow.repository.ScreenRepository;
import com.devesh.bookmyshow.repository.ScreenSeatRepository;
import com.devesh.bookmyshow.repository.ScreenSeatTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final TheaterService theaterService;
    private final ScreenSeatTypeRepository screenSeatTypeRepository;
    private final ScreenRepository screenRepository;
    private final ScreenSeatRepository screenSeatRepository;

    @Transactional
    public Screen createScreen(ScreenDTO screenDTO) {
        // Fetch theater
        Theater theater = theaterService.findTheaterById(screenDTO.getTheaterId());

        // Create Screen entity
        Screen screen = new Screen();
        screen.setScreenName(screenDTO.getScreenName());
        screen.setTheater(theater);

        List<ScreenSeat> screenSeats = new ArrayList<>();

        for (ScreenSeatDTO screenSeatDTO : screenDTO.getScreenSeats()) {
            // Create ScreenSeat entity
            ScreenSeat screenSeat = new ScreenSeat();
            screenSeat.setTotalRows(screenSeatDTO.getTotalRows());
            screenSeat.setTotalCols(screenSeatDTO.getTotalCols());
            screenSeat.setScreen(screen);

            // Create SeatType list
            List<ScreenSeatType> seatTypes = new ArrayList<>();
            for (ScreenSeatTypeDTO seatTypeDTO : screenSeatDTO.getSeatTypes()) {
                ScreenSeatType screenSeatType = new ScreenSeatType();
                screenSeatType.setSeatType(seatTypeDTO.getSeatType());
                screenSeatType.setSeatCount(seatTypeDTO.getSeatCount());
                screenSeatType.setBasePrice(seatTypeDTO.getBasePrice());
                screenSeatType.setScreenSeat(screenSeat);
                seatTypes.add(screenSeatType);
            }
            screenSeat.setSeatTypes(seatTypes);
            screenSeats.add(screenSeat);
        }

        // Set seats to screen
        screen.setScreenSeat(screenSeats.getFirst()); // Assuming one screen has one screen seat

        // Save entire structure in one go (cascade saves children)
        return screenRepository.save(screen);
    }
}
