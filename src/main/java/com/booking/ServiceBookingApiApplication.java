package com.booking;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServiceBookingApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServiceBookingApiApplication.class, args);
	}

}
