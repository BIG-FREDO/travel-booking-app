package com.fredo.book_travel.controller;

import com.fredo.book_travel.dto.request.BookingRequest.CreateBookingRequestDto;
import com.fredo.book_travel.dto.request.BookingRequest.UpdateBookingRequestDto;
import com.fredo.book_travel.dto.response.BookingResponseDto;
import com.fredo.book_travel.entity.Booking;
import com.fredo.book_travel.service.BookingService;
import org.hibernate.sql.Update;
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
    public List<BookingResponseDto> getBookings(){
        return bookingService.getBookings();
    }

    @GetMapping("/getBooking/{id}")
    public BookingResponseDto getBooking(@PathVariable("id") Integer id){
       return bookingService.getBooking(id);
    }

    @GetMapping("/getUserBookings/{id}")
    public List<BookingResponseDto> getUserBookings(@PathVariable Integer id){
        return bookingService.getUserBookings(id);
    }

    @PostMapping("/createBooking/{id}")
    public void createBooking(@RequestBody CreateBookingRequestDto dto, @PathVariable("id") Integer id){ bookingService.createBooking(dto, id);
    }

    @PutMapping("/update/{id}")
    public BookingResponseDto updateBooking(@PathVariable("id") Integer id, @RequestBody UpdateBookingRequestDto dto){
        return bookingService.updateBooking(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable("id") Integer id){
        bookingService.deleteBooking(id);
    }
}
