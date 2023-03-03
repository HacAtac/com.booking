package com.booking.entity;

import com.booking.util.AvailabilityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    private boolean isBookedAndPaid;

    // Constructor without ID and isBookedAndPaid parameter
    public Availability(Services services, Date startTime, Date endTime) {
        this.services = services;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Method to generate availability time slots
    public static List<Availability> generateAvailability(Services service, Date date) {
        return AvailabilityUtil.generateAvailability(service, date);
    }
}
