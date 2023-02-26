package com.booking.payload;

import lombok.Data;

@Data
public class MessageResponse {
    private int status;
    private String message;
    private String errorCode;

    public MessageResponse(int status, String message, String errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
