package com.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;
    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private Availability availability;
    @Column(nullable = false)
    private Date bookingDate;
    private String status;
    boolean isPaid;
    private String notes;
    private BigDecimal totalPrice;

    public void calculateTotalPrice() {
        BigDecimal price = this.service.getPrice();
        if(this.getService().getAdditionalPrice() != null){
            price = price.add(this.getService().getAdditionalPrice());
        }
        this.setTotalPrice(price);
    }

    @PostPersist
    @PostUpdate
    public void updateAvailability(){
        if (this.isPaid()){
            Availability availability = this.getAvailability();
            availability.setBookedAndPaid(true);
        }
    }
}
