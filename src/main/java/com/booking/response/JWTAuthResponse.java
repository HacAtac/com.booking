package com.booking.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "JWTAuthResponse", description = "JWTAuthResponse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
     @ApiModelProperty(value = "Access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmMiLCJleHAiOjE")
     private String accessToken;
     @ApiModelProperty(value = "Token type", example = "Bearer")
     private String tokenType = "Bearer";
}
