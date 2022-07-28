package com.appsdeveloper.estore.productservice.core.entity;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRestModel {

    @NotBlank(message = "Product title is required a title field")
    private String title;

    @Min(value = 1, message = "Price can not be less than 1")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity can not be lower than 1")
    @Max(value = 5, message = "Quantity can not be more than 5")
    private Integer quantity;
}
