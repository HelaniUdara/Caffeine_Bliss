package com.ex.caffeine_bliss.DTOs;

import com.ex.caffeine_bliss.entities.Customer;
import com.ex.caffeine_bliss.entities.User;
import com.ex.caffeine_bliss.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID id;
    private Customer customer;
    private User cashier;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private Date createdAt;
}
