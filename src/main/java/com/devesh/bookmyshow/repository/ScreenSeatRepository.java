package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.ScreenSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenSeatRepository extends JpaRepository<ScreenSeat, Long> {
}
