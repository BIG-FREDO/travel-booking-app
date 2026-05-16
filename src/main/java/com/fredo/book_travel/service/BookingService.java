package com.fredo.book_travel.service;

import com.fredo.book_travel.Mapper.BookingMapper;
import com.fredo.book_travel.dto.request.BookingRequest.CreateBookingRequestDto;
import com.fredo.book_travel.dto.request.BookingRequest.UpdateBookingRequestDto;
import com.fredo.book_travel.dto.response.BookingResponseDto;
import com.fredo.book_travel.entity.Booking;
import com.fredo.book_travel.entity.User;
import com.fredo.book_travel.repository.BookingRepository;
import com.fredo.book_travel.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    //--------GET ALL BOOKINGS--------
    public List<BookingResponseDto> getBookings(){
        return bookingRepository.findAll().stream().map(BookingMapper::ToResponseDto).toList();
    }

    //--------GET PARTICULAR BOOKING BY USING ITS ID--------
    public BookingResponseDto getBooking(Integer id, Authentication auth) {
        //----HERE WE SEARCH FOR THE CURRENT LOGGED-IN USER FROM THE JWT TOKEN THAT WILL BE PROVIDED IN THE HEADER
        String username = auth.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("You can only get your booking"));

        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking Not Found"));
        return BookingMapper.ToResponseDto(booking);
    }
    
    //---------GET A PARTICULAR USER'S BOOKINGS--------
    public List<BookingResponseDto> getUserBookings(Authentication auth) {

        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        return user.getBookings().stream().map(BookingMapper::ToResponseDto).toList();
    }

    //---------CREATING OR ADDING A NEW USER---------
    public String createBooking(CreateBookingRequestDto dto, Authentication auth) {

        //----HERE WE SEARCH FOR THE CURRENT LOGGED-IN USER FROM THE JWT TOKEN THAT WILL BE PROVIDED IN THE HEADER
        String username = auth.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));
       //---------CONVERTING TO ENTITY---------
       Booking updated = BookingMapper.toEntity(dto);

        updated.setLocalDateTime(LocalDateTime.now());
        if(updated.getDayOrNight().equalsIgnoreCase("Day")) updated.setDayOrNight("Takeoff time: 8:30AM");
        else if (updated.getDayOrNight().equalsIgnoreCase("Night")) updated.setDayOrNight("Takeoff time: 7:30PM");
        else throw new RuntimeException("Invalid Travel time. Please check spelling and try again.");

        //---------ASSIGNING OR ADDING THE CREATED BOOKING TO A SPECIFIC USER--------
        updated.setUser(user);
        Booking savedBooking = bookingRepository.save(updated);

        return user.getName() + "!\n You have successfully created a booking with ID: " + updated.getID() + "_####";
    }

    //--------THIS METHOD WILL BE RESPONSIBLE FOR THE PUT REQUEST LOGIC--------
    public BookingResponseDto updateBooking(Integer id, UpdateBookingRequestDto dto, Authentication auth) {
        //----HERE WE SEARCH FOR THE CURRENT LOGGED-IN USER FROM THE JWT TOKEN THAT WILL BE PROVIDED IN THE HEADER
        String username = auth.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Sorry! you can only edit your own bookings"));

        Booking existing = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking Not Found"));
        BookingMapper.UpdateToEntity(existing, dto);

        //---------CHECKING AND SETTING THE TAKEOFF TIME OF THE TRAVEL--------
        if(existing.getDayOrNight().equalsIgnoreCase("Day")) existing.setDayOrNight("Takeoff time: 8:30AM");
        else if (existing.getDayOrNight().equalsIgnoreCase("Night")) existing.setDayOrNight("Take off time: 7:30PM");
        else throw new RuntimeException("Invalid Travel time. Please check spelling and try again.");

        Booking saved = bookingRepository.save(existing);
        return BookingMapper.ToResponseDto(saved);
    }

    public String deleteBooking(Integer id, Authentication auth) {
        //----HERE WE SEARCH FOR THE CURRENT LOGGED-IN USER FROM THE JWT TOKEN THAT WILL BE PROVIDED IN THE HEADER
        String username = auth.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("You can only delete your own booking"));
        bookingRepository.deleteById(id);
        return "You have deleted a booking with ID: " + id + "_####";
    }
}
