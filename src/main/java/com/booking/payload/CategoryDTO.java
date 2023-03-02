package com.booking.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "CategoryDTO", description = "CategoryDTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @ApiModelProperty(value = "Category id", example = "1")
    private Long id;
    @ApiModelProperty(value = "Category name", example = "Category name")
    private String name;
    @ApiModelProperty(value = "Category description", example = "Category description")
    private String description;
}
