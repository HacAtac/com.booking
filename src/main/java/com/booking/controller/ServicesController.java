package com.booking.controller;

import com.booking.entity.Review;
import com.booking.entity.Services;
import com.booking.payload.ReviewDTO;
import com.booking.payload.ServiceDTO;
import com.booking.service.ReviewsService;
import com.booking.service.ServicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Services controller exposes REST APIs for services", value = "Services controller exposes REST APIs for services")
@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private  ServicesService servicesService;
    private ReviewsService reviewsService;

    public ServicesController(ServicesService servicesService, ReviewsService reviewsService) {
        this.servicesService = servicesService;
        this.reviewsService = reviewsService;
    }

    @ApiOperation(value = "Get services by category id REST API", notes = "Get services by category id REST API", response = ServiceDTO.class, responseContainer = "List")
    @GetMapping("/category/{id}")
   public ResponseEntity<List<ServiceDTO>> getServicesByCategoryId(@PathVariable("id") Long categoryId) {
        List<ServiceDTO> serviceDTOs = servicesService.getServicesByCategoryId(categoryId);
        return ResponseEntity.ok(serviceDTOs);
    }

    @ApiOperation(value = "Get all services REST API", notes = "Get all services REST API", response = ServiceDTO.class, responseContainer = "List")
    @GetMapping
    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> serviceDTOs = servicesService.getAllServices();
        for (ServiceDTO serviceDTO : serviceDTOs) {
            List<ReviewDTO> reviewDTOs = reviewsService.getReviewsByServiceId(serviceDTO.getId());
            serviceDTO.setReviews(reviewDTOs);
        }
        return serviceDTOs;
    }

    @ApiOperation(value = "Get service by id REST API", notes = "Get service by id REST API", response = ServiceDTO.class)
    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable Long id) {
        ServiceDTO serviceDTO = servicesService.getServiceById(id);
        List<ReviewDTO> reviewDTOs = reviewsService.getReviewsByServiceId(id);
        serviceDTO.setReviews(reviewDTOs);
        return serviceDTO;
    }


    @ApiOperation(value = "Create service REST API", notes = "Create service REST API", response = ServiceDTO.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
        Services createdService = servicesService.createService(serviceDTO);
        return servicesService.toServiceDTO(createdService);
    }

    @ApiOperation(value = "Update service REST API", notes = "Update service REST API", response = ServiceDTO.class)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ServiceDTO updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        Services updatedService = servicesService.updateService(id, serviceDTO);
        return servicesService.toServiceDTO(updatedService);
    }

    @ApiOperation(value = "Delete service REST API", notes = "Delete service REST API")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
    }

}

