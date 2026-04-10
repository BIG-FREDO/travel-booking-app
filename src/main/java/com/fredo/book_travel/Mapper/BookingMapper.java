package com.fredo.book_travel.Mapper;

import com.fredo.book_travel.dto.request.BookingRequest.CreateBookingRequestDto;
import com.fredo.book_travel.dto.request.BookingRequest.UpdateBookingRequestDto;
import com.fredo.book_travel.dto.response.BookingResponseDto;
import com.fredo.book_travel.entity.Booking;

public class BookingMapper {

    public static Booking toEntity(CreateBookingRequestDto bookingDto){

        Booking booking = new Booking();

        //----------CONVERTING BOOKING DTO TO ENTITY---------------
        booking.setDestination(bookingDto.destination());
        booking.setDayOrNight(bookingDto.DayOrNight());
        booking.setLocation(bookingDto.location());
        booking.setPersons(bookingDto.persons());
        return booking;
    }

    public static void UpdateToEntity(Booking booking, UpdateBookingRequestDto dto){
        booking.setDestination(dto.destination());
        booking.setDayOrNight(dto.DayOrNight());
        booking.setLocation(dto.location());
        booking.setPersons(dto.persons());

    }

    public static BookingResponseDto ToResponseDto(Booking booking){

        return new BookingResponseDto(
                booking.getLocation(),
                booking.getDestination(),
                booking.getDayOrNight(),
                booking.getPersons(),
                booking.getLocalDateTime()
                );

    }
}
