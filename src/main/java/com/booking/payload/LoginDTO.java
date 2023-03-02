package com.booking.payload;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "LoginDTO", description = "LoginDTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @ApiModelProperty(value = "User name or email", example = "HacAtac, hacatac@gmail.com")
    private String usernameOrEmail;
    @ApiModelProperty(value = "User password", example = "password123")
    private String password;
}
