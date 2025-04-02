package com.devesh.bookmyshow.repository;

import com.devesh.bookmyshow.entity.Screen;
import com.devesh.bookmyshow.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    boolean existsByTheaterAndScreenName(Theater theater, String screenName);
}
