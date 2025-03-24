package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}
