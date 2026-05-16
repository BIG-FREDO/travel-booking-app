package com.fredo.book_travel.controller;

import com.fredo.book_travel.dto.request.BookingRequest.CreateBookingRequestDto;
import com.fredo.book_travel.dto.request.BookingRequest.UpdateBookingRequestDto;
import com.fredo.book_travel.dto.response.BookingResponseDto;
import com.fredo.book_travel.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/getBookings")
    @PreAuthorize("hasAuthority('BACKEND_SETTINGS')")
    public List<BookingResponseDto> getBookings(){
        return bookingService.getBookings();
    }

    @GetMapping("/findBooking/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public BookingResponseDto getBooking(@PathVariable("id") Integer id, Authentication auth){
       return bookingService.getBooking(id, auth);
    }

    @GetMapping("/MyBookings")
    @PreAuthorize("hasAnyRole('USER')")
    public List<BookingResponseDto> getUserBookings(Authentication auth){
        return bookingService.getUserBookings(auth);
    }

    @PostMapping("/createBooking")
    @PreAuthorize("hasAuthority('BOOKING_CREATE')")
    public String createBooking(@RequestBody CreateBookingRequestDto dto, Authentication auth){ return bookingService.createBooking(dto, auth);
    }

    @PutMapping("/updateBooking/{id}")
    @PreAuthorize("hasAuthority('BOOKING_UPDATE')")
    public BookingResponseDto updateBooking(@PathVariable("id") Integer id, @RequestBody UpdateBookingRequestDto dto, Authentication auth){
        return bookingService.updateBooking(id,dto, auth);
    }

    @DeleteMapping("/deleteBooking/{id}")
    @PreAuthorize("hasAuthority('BOOKING_DELETE')")
    public String deleteBooking(@PathVariable("id") Integer id, Authentication auth){
        return bookingService.deleteBooking(id, auth);
    }
}
