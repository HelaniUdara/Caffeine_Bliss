package com.ex.caffeine_bliss.DTOs.request;

import com.ex.caffeine_bliss.entities.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddProductDTO {
    private String name;
    private ProductCategory category;
    private double unitPrice;
    private int stockQuantity;
}
