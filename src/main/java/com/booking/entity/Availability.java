package com.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    // Method to generate availability time slots
    public static List<Availability> generateAvailability(Services service, Date date) {
        List<Availability> availabilityList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int workDayDuration = 8; // In hours
        int maxTime = service.getMaxTime(); // In minutes
        int interval = 30; // In minutes
        calendar.set(Calendar.HOUR_OF_DAY, 9); // Start time
        calendar.set(Calendar.MINUTE, 0);
        while (calendar.get(Calendar.HOUR_OF_DAY) < 17) { // End time
            Date startTime = calendar.getTime();
            calendar.add(Calendar.MINUTE, interval);
            Date endTime = calendar.getTime();
            if (endTime.getTime() - startTime.getTime() <= maxTime * 60 * 1000) {
                Availability availability = new Availability();
                availability.setServices(service);
                availability.setStartTime(startTime);
                availability.setEndTime(endTime);
                availabilityList.add(availability);
            } else {
                calendar.add(Calendar.MINUTE, -interval);
            }
        }
        return availabilityList;
    }
}
