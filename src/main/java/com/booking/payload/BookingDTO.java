package com.booking.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long serviceId;
    private Long availabilityId;
    private Date bookingDate;
    private String status;
    private boolean isPaid;
    private String notes;
    private BigDecimal totalPrice;
}


