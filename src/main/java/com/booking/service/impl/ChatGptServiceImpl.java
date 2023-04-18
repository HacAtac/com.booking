package com.booking.service.impl;

import com.booking.ServiceBookingApiApplication;
import com.booking.service.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ChatGptServiceImpl implements ChatGptService {
    private static final String engine_id = "text-davinci-003";

    private static final String API_URL = "https://api.openai.com/v1/engines/" + engine_id + "/completions";

    @Override
    public String getChatGptResponse(String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + ServiceBookingApiApplication.apiKey); // Use the API key

            // Create the request body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("prompt", prompt);
            requestBody.put("max_tokens", 150);

            // Create the HTTP entity
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // Make the POST request and get the response
            ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, entity, Map.class);

            // Extract the generated text from the response body
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    String generatedText = (String) choices.get(0).get("text");
                    return generatedText;
                    //Format the response to use custom response object
                    //ChatGptResponse chatGptResponse = new ChatGptResponse(generatedText);
                    //return chatGptResponse;
                }
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
        return null;
    }
}
