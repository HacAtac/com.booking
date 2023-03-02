package com.booking.payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "ReviewDTO", description = "ReviewDTO")
@Data
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
}
