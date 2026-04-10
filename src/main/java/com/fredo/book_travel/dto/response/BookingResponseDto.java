package com.fredo.book_travel.dto.response;

import java.time.LocalDateTime;

public record BookingResponseDto(
        String location,
        String destination,
        String DayOrNight,
        long persons,
        LocalDateTime localDateTime
        ) {}
