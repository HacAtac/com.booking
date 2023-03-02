package com.booking.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;

@ApiModel(value = "MessageResponse", description = "MessageResponse")
@Data
public class MessageResponse {
    @ApiModelProperty(value = "Response status", example = "200, 401, 500, etc.")
    private int status;
    @ApiModelProperty(value = "Response message", example = "Success, Error, Invalid JWT Token, etc.")
    private String message;
    @ApiModelProperty(value = "Response error code", example = "E001, E002, etc.")
    private String errorCode;

    public MessageResponse(int status, String message, String errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
