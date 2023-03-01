package com.booking.service;

import com.booking.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategory(Long categoryId);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
}
