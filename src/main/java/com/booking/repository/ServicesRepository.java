package com.booking.repository;

import com.booking.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    List<Services> findByCategoryId(Long categoryId);
}
