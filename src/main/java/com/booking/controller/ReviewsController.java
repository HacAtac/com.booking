package com.booking.controller;

import com.booking.entity.Review;
import com.booking.payload.ReviewDTO;
import com.booking.service.ReviewsService;
import com.booking.service.ServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    private ServicesService servicesService;
    private ReviewsService reviewsService;

    public ReviewsController(ServicesService servicesService, ReviewsService reviewsService) {
        this.servicesService = servicesService;
        this.reviewsService = reviewsService;
    }

    @PostMapping("/service/{serviceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDTO createReview(@PathVariable Long serviceId, @RequestBody ReviewDTO reviewDTO) {
        Review createdReview = reviewsService.createReview(serviceId, reviewDTO);
        return reviewsService.toReviewDTO(createdReview);
    }

    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        ReviewDTO reviewDTO = reviewsService.getReviewById(id);
        return reviewDTO;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        Review updatedReview = reviewsService.updateReview(id, reviewDTO);
        return reviewsService.toReviewDTO(updatedReview);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteReview(@PathVariable Long id) {
        reviewsService.deleteReview(id);
    }

    //ID param will be ID of service
    @GetMapping("/service/{id}")
    public List<ReviewDTO> getReviewsByServiceId(@PathVariable Long id) {
        return reviewsService.getReviewsByServiceId(id);
    }
}
