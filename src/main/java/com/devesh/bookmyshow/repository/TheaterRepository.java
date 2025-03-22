package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    void deleteByTheaterName(String theaterName);

    Theater getTheaterByTheaterName(String theaterName);
}
