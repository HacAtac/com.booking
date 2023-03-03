package com.booking.repository;

import com.booking.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long>{
    @Query("SELECT a FROM Availability a WHERE a.services.id = :serviceId AND a.startTime >= :startTime AND a.endTime <= :endTime AND a.isBooked = false")
    List<Availability> findAvailableTimeSlots(@Param("serviceId") Long serviceId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

}
