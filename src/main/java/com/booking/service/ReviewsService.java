package com.booking.service;

import com.booking.entity.Review;
import com.booking.entity.Services;
import com.booking.entity.User;
import com.booking.exceptions.EntityNotFoundException;
import com.booking.exceptions.ServiceNotFoundException;
import com.booking.payload.ReviewDTO;
import com.booking.payload.UserDTO;
import com.booking.repository.ReviewRepository;
import com.booking.repository.ServicesRepository;
import com.booking.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final ServicesRepository serviceRepository;

    public ReviewsService(ReviewRepository reviewRepository, ServicesRepository serviceRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
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

    public Review createReview(Long serviceId, ReviewDTO reviewDTO, Authentication authentication) throws ServiceNotFoundException {
        String username = authentication.getName();
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found with id: " + serviceId));

        Review review = new Review();
        review.setText(reviewDTO.getText());
        review.setRating(reviewDTO.getRating());
        review.setUser(user);
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
       Review review = toReview(getReviewById(id));
       if(review == null || review.getId() == null) {
           throw new EntityNotFoundException("Review", id);
         }
         reviewRepository.delete(review);
    }

    //checks if the user is the owner of the review. Useful for allowing users to delete/update their own reviews
    public boolean isReviewOwner(Authentication authentication, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review", reviewId));
        String username = authentication.getName();
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return review.getUser().getId().equals(user.getId());
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

        User user = review.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles());

        reviewDTO.setUser(userDTO);
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

