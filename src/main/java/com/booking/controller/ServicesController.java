package com.booking.controller;

import com.booking.entity.Review;
import com.booking.entity.Services;
import com.booking.payload.ReviewDTO;
import com.booking.payload.ServiceDTO;
import com.booking.service.ReviewsService;
import com.booking.service.ServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private  ServicesService servicesService;
    private ReviewsService reviewsService;

    public ServicesController(ServicesService servicesService, ReviewsService reviewsService) {
        this.servicesService = servicesService;
        this.reviewsService = reviewsService;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> serviceDTOs = servicesService.getAllServices();
        for (ServiceDTO serviceDTO : serviceDTOs) {
            List<ReviewDTO> reviewDTOs = reviewsService.getReviewsByServiceId(serviceDTO.getId());
            serviceDTO.setReviews(reviewDTOs);
        }
        return serviceDTOs;
    }

    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable Long id) {
        ServiceDTO serviceDTO = servicesService.getServiceById(id);
        List<ReviewDTO> reviewDTOs = reviewsService.getReviewsByServiceId(id);
        serviceDTO.setReviews(reviewDTOs);
        return serviceDTO;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
        Services createdService = servicesService.createService(serviceDTO);
        return servicesService.toServiceDTO(createdService);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ServiceDTO updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        Services updatedService = servicesService.updateService(id, serviceDTO);
        return servicesService.toServiceDTO(updatedService);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
    }

}

