package com.booking.service;

import com.booking.entity.Category;
import com.booking.entity.Review;
import com.booking.entity.Services;
import com.booking.exceptions.ApiException;
import com.booking.exceptions.ControllerExceptionAdvice;
import com.booking.exceptions.EntityNotFoundException;
import com.booking.payload.MessageResponse;
import com.booking.payload.ReviewDTO;
import com.booking.payload.ServiceDTO;
import com.booking.repository.CategoryRepository;
import com.booking.repository.ReviewRepository;
import com.booking.repository.ServicesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private ServicesRepository servicesRepository;
    private ReviewRepository reviewRepository;
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ServicesService(ServicesRepository servicesRepository,
                           ReviewRepository reviewRepository,
                           CategoryRepository categoryRepository,
                           ModelMapper modelMapper) {
        this.servicesRepository = servicesRepository;
        this.reviewRepository = reviewRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
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
        Category category = categoryRepository.findById(serviceDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category", serviceDTO.getCategoryId()));
        // Check if the user has the role of admin
        Services service = toServiceEntity(serviceDTO);
        service.setCategory(category);
        return servicesRepository.save(service);
    }

    public Services updateService(Long id, ServiceDTO serviceDTO) {
        Services existingService = servicesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service", id));
        Category category = categoryRepository.findById(serviceDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category", serviceDTO.getCategoryId()));
        Services updatedService = toServiceEntity(serviceDTO);
        existingService.setName(updatedService.getName());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        existingService.setPhoto(updatedService.getPhoto());
        existingService.setAdditionalPrice(updatedService.getAdditionalPrice());
        existingService.setCategory(category);
        return servicesRepository.save(existingService);
    }

    @Transactional
    public void deleteService(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service", id));
        //iterate through reviews and delete them if there is any attached to the service
        reviewRepository.findByServiceId(id).forEach(reviewRepository::delete);
        servicesRepository.delete(service);

    }


    public ServiceDTO toServiceDTO(Services service) {
        return modelMapper.map(service, ServiceDTO.class);
    }

    public ReviewDTO toReviewDTO(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

    private Services toServiceEntity(ServiceDTO serviceDTO) {
        return modelMapper.map(serviceDTO, Services.class);
    }
}
