package com.booking.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "ServiceDTO", description = "ServiceDTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    @ApiModelProperty(value = "Service id", example = "1")
    private Long id;
    @ApiModelProperty(value = "Service name", example = "Service name")
    private String name;
    @ApiModelProperty(value = "Service description", example = "Service description")
    private String description;
    @ApiModelProperty(value = "Service price", example = "Service price")
    private String price;
    @ApiModelProperty(value = "Service photo", example = "Service photo")
    private String photo;
    @ApiModelProperty(value = "Service additional price", example = "Service additional price")
    private String additionalPrice;
    @ApiModelProperty(value = "Service category id", example = "Service category id")
    private Long categoryId;
    @ApiModelProperty(value = "List of reviews", example = "List of reviews", dataType = "ReviewDTO")
    private List<ReviewDTO> reviews;
}

