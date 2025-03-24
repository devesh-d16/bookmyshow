package com.devesh.bookmyshow.enums;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public enum ShowTimingType {
    MORNING(0.80, LocalTime.of(9, 0), LocalTime.of(12, 0)),
    AFTERNOON(1.0, LocalTime.of(12, 0), LocalTime.of(18, 0)),
    NIGHT(1.20, LocalTime.of(18, 0), LocalTime.of(23, 59));

    private final double priceMultiplier;
    private final LocalTime startTime;
    private final LocalTime endTime;

    ShowTimingType(double priceMultiplier, LocalTime startTime, LocalTime endTime) {
        this.priceMultiplier = priceMultiplier;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public static ShowTimingType getShowTimingType(LocalDateTime showStartTime) {
        for (ShowTimingType timingType : values()) {
            if (isWithinRange(showStartTime.toLocalTime(), timingType.startTime, timingType.endTime)) {
                return timingType;
            }
        }
        throw new RuntimeException("Invalid show time");
    }

    private static boolean isWithinRange(LocalTime showTime, LocalTime startTime, LocalTime endTime) {
        return !showTime.isBefore(startTime) && !showTime.isAfter(endTime);
    }
}
