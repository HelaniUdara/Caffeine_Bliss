package com.ex.caffeine_bliss.DTOs;

import com.ex.caffeine_bliss.entities.Order;
import com.ex.caffeine_bliss.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDTO {
    private UUID id;
    private Order order;
    private Product product;
    private int quantity;
    private double price;
}
