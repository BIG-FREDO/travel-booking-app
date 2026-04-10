package com.fredo.book_travel.repository;

import com.fredo.book_travel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
