package com.booking;

import com.booking.util.DotEnvReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;


@SpringBootApplication
public class ServiceBookingApiApplication {

	public static String apiKey;


	public static void main(String[] args) {
		// Read the .env file (specify the path to your .env file here)
		Map<String, String> envVariables = DotEnvReader.readEnvFile(".env");
		apiKey = envVariables.get("app-openai-api-key"); // Set the value of the API key
		SpringApplication.run(ServiceBookingApiApplication.class, args);
	}

}
