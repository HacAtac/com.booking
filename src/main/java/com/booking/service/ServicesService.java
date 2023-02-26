package com.booking.service;

import com.booking.entity.Review;
import com.booking.entity.Services;
import com.booking.exceptions.EntityNotFoundException;
import com.booking.payload.ReviewDTO;
import com.booking.payload.ServiceDTO;
import com.booking.repository.ReviewRepository;
import com.booking.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private ServicesRepository servicesRepository;
    private ReviewRepository reviewRepository;

    public ServicesService(ServicesRepository servicesRepository, ReviewRepository reviewRepository) {
        this.servicesRepository = servicesRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<ServiceDTO> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return services.stream().map(this::toServiceDTO).collect(Collectors.toList());
    }

    public ServiceDTO getServiceById(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service", id));
        return toServiceDTO(service);
    }

    public List<ReviewDTO> getReviewsByServiceId(Long serviceId) {
        List<Review> reviews = reviewRepository.findByServiceId(serviceId);
        return reviews.stream().map(this::toReviewDTO).collect(Collectors.toList());
    }

    public Services createService(ServiceDTO serviceDTO) {
        Services service = toServiceEntity(serviceDTO);
        return servicesRepository.save(service);
    }

    public Services updateService(Long id, ServiceDTO serviceDTO) {
        Services existingService = servicesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service", id));
        Services updatedService = toServiceEntity(serviceDTO);
        existingService.setName(updatedService.getName());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        existingService.setPhoto(updatedService.getPhoto());
        existingService.setAdditionalPrice(updatedService.getAdditionalPrice());
        return servicesRepository.save(existingService);
    }

    public void deleteService(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service", id));
        reviewRepository.deleteByServiceId(id);
        servicesRepository.delete(service);
    }


    public ServiceDTO toServiceDTO(Services service) {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(service.getId());
        serviceDTO.setName(service.getName());
        serviceDTO.setDescription(service.getDescription());
        serviceDTO.setPrice(service.getPrice());
        serviceDTO.setPhoto(service.getPhoto());
        serviceDTO.setAdditionalPrice(service.getAdditionalPrice());
        return serviceDTO;
    }

    private ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setText(review.getText());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setServiceId(review.getService().getId());
        return reviewDTO;
    }

    private Services toServiceEntity(ServiceDTO serviceDTO) {
        Services service = new Services();
        service.setId(serviceDTO.getId());
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setPhoto(serviceDTO.getPhoto());
        service.setAdditionalPrice(serviceDTO.getAdditionalPrice());
        return service;
    }
}
