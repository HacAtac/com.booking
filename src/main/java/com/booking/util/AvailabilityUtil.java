package com.booking.util;

import com.booking.entity.Availability;
import com.booking.entity.Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AvailabilityUtil {
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

