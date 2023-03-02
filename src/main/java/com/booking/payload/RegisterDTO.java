package com.booking.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "RegisterDTO", description = "RegisterDTO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @ApiModelProperty(value = "User name", example = "Hack Attack")
    private String name;
    @ApiModelProperty(value = "User surname", example = "HacAtac")
    private String username;
    @ApiModelProperty(value = "User email", example = "email@email.com")
    private String email;
    @ApiModelProperty(value = "User password", example = "password123")
    private String password;
}
