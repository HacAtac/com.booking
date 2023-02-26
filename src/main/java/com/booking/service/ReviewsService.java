package com.booking.service;

import com.booking.entity.Review;
import com.booking.entity.Services;
import com.booking.exceptions.EntityNotFoundException;
import com.booking.payload.ReviewDTO;
import com.booking.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private final ReviewRepository reviewRepository;

    public ReviewsService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDTO> getReviewsByServiceId(Long serviceId) {
        List<Review> reviews = reviewRepository.findByServiceId(serviceId);
        return toReviewDTOs(reviews);
    }

    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review", id));
        return toReviewDTO(review);
    }

    public Review createReview(Long serviceId, ReviewDTO reviewDTO) {
        Review review = toReview(reviewDTO);
        Services service = new Services();
        service.setId(serviceId);
        review.setService(service);
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review", id));
        review.setText(reviewDTO.getText());
        review.setRating(reviewDTO.getRating());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review", id));
        reviewRepository.delete(review);
    }

    public List<ReviewDTO> toReviewDTOs(List<Review> reviews) {
        return reviews.stream().map(this::toReviewDTO).collect(Collectors.toList());
    }

    public ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setText(review.getText());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setServiceId(review.getService().getId());
        return reviewDTO;
    }

    public Review toReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setText(reviewDTO.getText());
        review.setRating(reviewDTO.getRating());
        return review;
    }
}

