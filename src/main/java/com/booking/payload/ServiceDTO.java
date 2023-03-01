package com.booking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private String price;
    private String photo;
    private String additionalPrice;
    private Long categoryId;
    private List<ReviewDTO> reviews;
}

