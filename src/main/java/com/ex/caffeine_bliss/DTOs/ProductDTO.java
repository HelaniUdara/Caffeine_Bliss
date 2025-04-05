package com.ex.caffeine_bliss.DTOs;


import com.ex.caffeine_bliss.entities.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private ProductCategory category;
    private double price;
    private int stockQuantity;
    private Date createdAt;
    private Date updatedAt;
}
