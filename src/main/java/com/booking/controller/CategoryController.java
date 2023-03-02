package com.booking.controller;

import com.booking.payload.CategoryDTO;
import com.booking.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Category controller exposes REST APIs for categories", value = "Category controller exposes REST APIs for categories")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build Add Category REST API
    @ApiOperation(value = "Create category REST API", notes = "Create category REST API", response = CategoryDTO.class)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
       CategoryDTO saveCategory = categoryService.addCategory(categoryDTO);
       return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get category by id REST API", notes = "Get category by id REST API", response = CategoryDTO.class)
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long categoryId) {
       CategoryDTO categoryDto = categoryService.getCategory(categoryId);
       return ResponseEntity.ok(categoryDto);
    }

    @ApiOperation(value = "Get all categories REST API", notes = "Get all categories REST API", response = CategoryDTO.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
       return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @ApiOperation(value = "Update category REST API", notes = "Update category REST API", response = CategoryDTO.class)
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDTO categoryDTO) {
       CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
       return ResponseEntity.ok(updatedCategory);
    }

    @ApiOperation(value = "Delete category REST API", notes = "Delete category REST API", response = CategoryDTO.class)
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Long categoryId) {
       CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
       return ResponseEntity.ok(deletedCategory);
    }
}
