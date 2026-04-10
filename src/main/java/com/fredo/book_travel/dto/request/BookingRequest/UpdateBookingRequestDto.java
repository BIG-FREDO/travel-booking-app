package com.fredo.book_travel.dto.request.BookingRequest;

public record UpdateBookingRequestDto(
                                      String location,
                                      String destination,
                                      String DayOrNight,
                                      long persons
) {}
