package com.booking.controller;

import com.booking.entity.Review;
import com.booking.payload.ReviewDTO;
import com.booking.service.ReviewsService;
import com.booking.service.ServicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Reviews controller exposes REST APIs for reviews", value = "Reviews controller exposes REST APIs for reviews")
@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    private ServicesService servicesService;
    private ReviewsService reviewsService;

    public ReviewsController(ServicesService servicesService, ReviewsService reviewsService) {
        this.servicesService = servicesService;
        this.reviewsService = reviewsService;
    }

    @ApiOperation(value = "Create review REST API", notes = "Create review REST API", response = ReviewDTO.class)
    @PostMapping("/service/{serviceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDTO createReview(@PathVariable Long serviceId, @RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        Review createdReview = reviewsService.createReview(serviceId, reviewDTO, authentication);
        return reviewsService.toReviewDTO(createdReview);
    }

    @ApiOperation(value = "Get all reviews REST API", notes = "Get all reviews REST API", response = ReviewDTO.class, responseContainer = "List")
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        ReviewDTO reviewDTO = reviewsService.getReviewById(id);
        return reviewDTO;
    }

    @ApiOperation(value = "Get all reviews REST API", notes = "Get all reviews REST API", response = ReviewDTO.class, responseContainer = "List")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @reviewsService.isReviewOwner(authentication, #id)")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        Review updatedReview = reviewsService.updateReview(id, reviewDTO);
        return reviewsService.toReviewDTO(updatedReview);
    }

    @ApiOperation(value = "Delete review REST API", notes = "Delete review REST API")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @reviewsService.isReviewOwner(authentication, #id)")
    public void deleteReview(@PathVariable Long id) {
        reviewsService.deleteReview(id);
    }

    //ID param will be ID of service
    @ApiOperation(value = "Get reviews by service id REST API", notes = "Get reviews by service id REST API", response = ReviewDTO.class, responseContainer = "List")
    @GetMapping("/service/{id}")
    public List<ReviewDTO> getReviewsByServiceId(@PathVariable Long id) {
        return reviewsService.getReviewsByServiceId(id);
    }
}
