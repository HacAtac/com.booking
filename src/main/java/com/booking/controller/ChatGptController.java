package com.booking.controller;

import com.booking.response.ChatGptResponse;
import com.booking.service.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chatgpt")
@Slf4j
public class ChatGptController {

    @Autowired
    private ChatGptService chatGptService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String chatGpt(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        return chatGptService.getChatGptResponse(prompt);
    }

}
