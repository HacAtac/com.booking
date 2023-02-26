package com.booking.repository;

import com.booking.entity.Services;
import com.booking.payload.ReviewDTO;
import com.booking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByServiceId(Long serviceId);

   void deleteByServiceId(Long serviceId);
}
