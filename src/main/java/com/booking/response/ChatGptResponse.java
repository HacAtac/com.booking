package com.booking.response;

public class ChatGptResponse {
    private String generatedText;

    public ChatGptResponse(String generatedText) {
        this.generatedText = generatedText;
    }

    // Getters and setters
    public String getGeneratedText() {
        return generatedText;
    }

    public void setGeneratedText(String generatedText) {
        this.generatedText = generatedText;
    }
}
