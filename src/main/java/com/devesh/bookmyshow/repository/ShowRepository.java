package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.Movie;
import com.devesh.bookmyshow.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovie(Movie movie);

    Show getShowByShowId(Long showId);

    List<Show> findByScreen_ScreenId(Long screenScreenId);
}
