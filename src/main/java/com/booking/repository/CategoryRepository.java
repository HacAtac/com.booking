package com.booking.repository;

import com.booking.entity.Category;
import com.booking.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
