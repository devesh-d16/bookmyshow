package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.ShowDTO;
import com.devesh.bookmyshow.dto.ShowResponseDTO;
import com.devesh.bookmyshow.dto.ShowSeatDTO;
import com.devesh.bookmyshow.entity.*;
import com.devesh.bookmyshow.enums.SeatStatus;
import com.devesh.bookmyshow.enums.ShowTimingType;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    @Transactional
    public ShowResponseDTO createShow(ShowDTO showDTO) {
        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        Screen screen = screenRepository.findById(showDTO.getScreenId())
                .orElseThrow(() -> new EntityNotFoundException("Screen not found"));

        validateShowTiming(showDTO, movie, screen);

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

    private void validateShowTiming(ShowDTO showDTO, Movie movie, Screen screen) {
        if (!showDTO.getStartTime().isBefore(showDTO.getEndingTime())) {
            throw new InvalidRequestException("Show start time must be before end time.");
        }

        long showDurationInMinutes = Duration.between(
                showDTO.getStartTime(), showDTO.getEndingTime()).toMinutes();

        if (showDurationInMinutes < movie.getDuration()) {
            throw new InvalidRequestException("The show duration (" + showDurationInMinutes +
                    " minutes) is shorter than the movie duration (" + movie.getDuration() + " minutes).");
        }

        List<Show> existingShows = showRepository.findByScreen_ScreenId(screen.getScreenId());
        boolean isOverlap = existingShows.stream().anyMatch(existingShow ->
                showDTO.getStartTime().isBefore(existingShow.getEndingTime()) &&
                        showDTO.getEndingTime().isAfter(existingShow.getStartTime())
        );

        if (isOverlap) {
            throw new InvalidRequestException("This screen already has a show scheduled during the selected time.");
        }
    }

    private List<ShowSeat> generateShowSeats(Show show, Screen screen) {
        if (screen.getScreenSeat() == null || screen.getScreenSeat().getSeatTypes().isEmpty()) {
            throw new EntityNotFoundException("Screen does not have seat configuration");
        }

        ShowTimingType showTimingType = ShowTimingType.getShowTimingType(show.getStartTime());

        return screen.getScreenSeat().getSeatTypes().stream()
                .flatMap(seatType -> IntStream.rangeClosed(1, seatType.getSeatCount())
                        .mapToObj(i -> createShowSeat(i, seatType, show, screen, showTimingType)))
                .collect(Collectors.toList());
    }

    private ShowSeat createShowSeat(int seatNumber, ScreenSeatType seatType,
                                    Show show, Screen screen, ShowTimingType timingType) {
        ShowSeat showSeat = new ShowSeat();
        showSeat.setSeatNumber(seatNumber);
        showSeat.setSeatType(seatType.getSeatType());
        showSeat.setSeatStatus(SeatStatus.AVAILABLE);
        showSeat.setPrice(seatType.getBasePrice() * timingType.getPriceMultiplier());
        showSeat.setShow(show);
        showSeat.setScreen(screen);
        return showSeat;
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

    public Show getShowById(Long showId) {
        return showRepository.getShowByShowId(showId);
    }
}
