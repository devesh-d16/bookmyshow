package com.devesh.bookmyshow.service;


import com.devesh.bookmyshow.dto.*;
import com.devesh.bookmyshow.entity.Screen;
import com.devesh.bookmyshow.entity.ScreenSeat;
import com.devesh.bookmyshow.entity.ScreenSeatType;
import com.devesh.bookmyshow.entity.Theater;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.repository.ScreenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final TheaterService theaterService;
    private final ScreenRepository screenRepository;

    @Transactional
    public ScreenResponseDTO createScreen(ScreenDTO screenDTO) {
        Theater theater = theaterService.findTheaterById(screenDTO.getTheaterId());
        validateScreenDTO(screenDTO);

        if (screenRepository.existsByTheaterAndScreenName(theater, screenDTO.getScreenName())) {
            throw new InvalidRequestException(
                    "A screen named '" + screenDTO.getScreenName() + "' already exists in this theater."
            );
        }

        Screen screen = buildScreen(screenDTO, theater);
        screen = screenRepository.save(screen);

        return mapToScreenResponseDTO(screen);
    }

    private void validateScreenDTO(ScreenDTO screenDTO) {
        if (screenDTO.getScreenName() == null || screenDTO.getScreenName().isBlank()) {
            throw new InvalidRequestException("Screen name cannot be blank");
        }
        if (screenDTO.getScreenSeats() == null || screenDTO.getScreenSeats().isEmpty()) {
            throw new InvalidRequestException("Screen must have at least one seat configuration.");
        }
    }

    private Screen buildScreen(ScreenDTO screenDTO, Theater theater) {
        Screen screen = new Screen();
        screen.setScreenName(screenDTO.getScreenName());
        screen.setTheater(theater);

        // Use the first seat configuration
        ScreenSeatDTO seatDTO = screenDTO.getScreenSeats().get(0);
        ScreenSeat screenSeat = buildScreenSeat(seatDTO, screen);
        screen.setScreenSeat(screenSeat);

        return screen;
    }

    private ScreenSeat buildScreenSeat(ScreenSeatDTO screenSeatDTO, Screen screen) {
        if (screenSeatDTO.getTotalRows() < 1 || screenSeatDTO.getTotalCols() < 1) {
            throw new InvalidRequestException("Rows and columns must be >= 1");
        }

        ScreenSeat screenSeat = new ScreenSeat();
        screenSeat.setTotalRows(screenSeatDTO.getTotalRows());
        screenSeat.setTotalCols(screenSeatDTO.getTotalCols());
        screenSeat.setScreen(screen);

        List<ScreenSeatType> seatTypes = buildSeatTypes(screenSeatDTO.getSeatTypes(), screenSeat);
        screenSeat.setSeatTypes(seatTypes);

        return screenSeat;
    }

    private List<ScreenSeatType> buildSeatTypes(List<ScreenSeatTypeDTO> seatTypeDTOs, ScreenSeat screenSeat) {
        return seatTypeDTOs.stream().map(dto -> {
            if (dto.getSeatCount() < 1) {
                throw new InvalidRequestException("Seat count must be >= 1");
            }
            if (dto.getBasePrice() < 0) {
                throw new InvalidRequestException("Base price must be >= 0");
            }
            ScreenSeatType seatType = new ScreenSeatType();
            seatType.setScreenSeat(screenSeat);
            seatType.setSeatType(dto.getSeatType());
            seatType.setSeatCount(dto.getSeatCount());
            seatType.setBasePrice(dto.getBasePrice());
            return seatType;
        }).collect(Collectors.toList());
    }

    private ScreenResponseDTO mapToScreenResponseDTO(Screen screen) {
        ScreenResponseDTO responseDTO = new ScreenResponseDTO();
        responseDTO.setScreenName(screen.getScreenName());
        responseDTO.setScreenId(screen.getScreenId());

        Theater theater = screen.getTheater();
        TheaterDTO theaterDTO = new TheaterDTO();
        theaterDTO.setTheaterName(theater.getTheaterName());
        theaterDTO.setCityName(theater.getCity().getCityName());
        theaterDTO.setTheaterId(theater.getTheaterId());
        responseDTO.setTheaterDetail(theaterDTO);

        if (screen.getScreenSeat() != null) {
            ScreenSeat screenSeat = screen.getScreenSeat();

            ScreenSeatDTO screenSeatDTO = new ScreenSeatDTO();
            screenSeatDTO.setTotalRows(screenSeat.getTotalRows());
            screenSeatDTO.setTotalCols(screenSeat.getTotalCols());

            List<ScreenSeatTypeDTO> seatTypeDTOs = screenSeat.getSeatTypes()
                    .stream()
                    .map(st -> {
                        ScreenSeatTypeDTO typeDTO = new ScreenSeatTypeDTO();
                        typeDTO.setSeatType(st.getSeatType());
                        typeDTO.setSeatCount(st.getSeatCount());
                        typeDTO.setBasePrice(st.getBasePrice());
                        return typeDTO;
                    })
                    .collect(Collectors.toList());

            screenSeatDTO.setSeatTypes(seatTypeDTOs);
            responseDTO.setSeatDetails(screenSeatDTO);
        }

        return responseDTO;
    }
}
