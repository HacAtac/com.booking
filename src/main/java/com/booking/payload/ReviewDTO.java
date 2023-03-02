package com.booking.payload;
import com.booking.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "ReviewDTO", description = "ReviewDTO")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    @ApiModelProperty(value = "Review id", example = "1")
    private Long id;
    @ApiModelProperty(value = "Review text")
    private String text;
    @ApiModelProperty(value = "Review rating")
    private int rating;
    @ApiModelProperty(value = "Review user id")
    private Long serviceId;
    @ApiModelProperty(value = "User object")
    private UserDTO user;
}
