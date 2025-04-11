package com.ex.caffeine_bliss.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderItemDTO {
    private String productName;
    private double unitPrice;
    private int quantity;
    private double amount;
}
