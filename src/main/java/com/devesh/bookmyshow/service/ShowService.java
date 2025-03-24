package com.devesh.bookmyshow.service;


import com.devesh.bookmyshow.dto.ShowDTO;
import com.devesh.bookmyshow.dto.ShowResponseDTO;
import com.devesh.bookmyshow.dto.ShowSeatDTO;
import com.devesh.bookmyshow.entity.*;
import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.enums.ShowTimingType;
import com.devesh.bookmyshow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ShowService {

    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    public ShowResponseDTO createShow(ShowDTO showDTO) {
        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Screen screen = screenRepository.findById(showDTO.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(showDTO.getStartTime());
        show.setEndingTime(showDTO.getEndingTime());

        Show savedShow = showRepository.save(show);

        List<ShowSeat> showSeats = generateShowSeats(savedShow, screen);
        showSeatRepository.saveAll(showSeats);

        return mapToShowResponseDTO(savedShow, showSeats);
    }

    private List<ShowSeat> generateShowSeats(Show show, Screen screen) {
        List<ShowSeat> showSeats = new ArrayList<>();

        if (screen.getScreenSeat() == null || screen.getScreenSeat().getSeatTypes().isEmpty()) {
            throw new RuntimeException("Screen does not have seat configuration");
        }

        // Get price multiplier from ShowTimingType
        ShowTimingType showTimingType = ShowTimingType.getShowTimingType(show.getStartTime());

        for (ScreenSeatType seatType : screen.getScreenSeat().getSeatTypes()) {
            double finalPrice = seatType.getBasePrice() * showTimingType.getPriceMultiplier();

            for (int i = 1; i <= seatType.getSeatCount(); i++) {
                ShowSeat showSeat = new ShowSeat();
                showSeat.setSeatNumber(i);
                showSeat.setSeatType(seatType.getSeatType());
                showSeat.setSeatStatus(SeatStatus.AVAILABLE);
                showSeat.setPrice(finalPrice);
                showSeat.setShow(show);
                showSeat.setScreen(screen);

                showSeats.add(showSeat);
            }
        }
        return showSeats;
    }

    private ShowResponseDTO mapToShowResponseDTO(Show show, List<ShowSeat> showSeats) {
        return new ShowResponseDTO(
                show.getShowId(),
                show.getMovie().getTitle(),
                show.getScreen().getScreenName(),
                show.getStartTime(),
                show.getEndingTime(),
                showSeats.stream().map(this::mapToShowSeatDTO).collect(Collectors.toList())
        );
    }

    private ShowSeatDTO mapToShowSeatDTO(ShowSeat showSeat) {
        return new ShowSeatDTO(
                showSeat.getSeatId(),
                showSeat.getSeatNumber(),
                showSeat.getSeatType(),
                showSeat.getSeatStatus(),
                showSeat.getPrice()
        );
    }
}
