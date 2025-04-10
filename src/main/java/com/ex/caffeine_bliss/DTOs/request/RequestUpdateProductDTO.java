package com.ex.caffeine_bliss.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateProductDTO {
    private UUID id;
    private double unitPrice;
    private int stockQuantity;
}
