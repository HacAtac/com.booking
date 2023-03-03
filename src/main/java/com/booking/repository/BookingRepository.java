package com.booking.repository;

import com.booking.entity.Availability;
import com.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.service.id = :serviceId AND b.bookingDate = :date")
    List<Booking> findBookingsByServiceAndDate(@Param("serviceId") Long serviceId, @Param("date") Date date);

    @Query("SELECT a FROM Availability a WHERE a.services.id = :serviceId AND a.startTime >= :startTime AND a.endTime <= :endTime AND a.isBooked = false")
    List<Availability> findAvailableTimeSlots(@Param("serviceId") Long serviceId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
