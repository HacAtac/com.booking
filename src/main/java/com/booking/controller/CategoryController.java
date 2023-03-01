package com.booking.controller;

import com.booking.payload.CategoryDTO;
import com.booking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build Add Category REST API
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
       CategoryDTO saveCategory = categoryService.addCategory(categoryDTO);
       return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long categoryId) {
       CategoryDTO categoryDto = categoryService.getCategory(categoryId);
       return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
       return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDTO categoryDTO) {
       CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
       return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Long categoryId) {
       CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
       return ResponseEntity.ok(deletedCategory);
    }
}
