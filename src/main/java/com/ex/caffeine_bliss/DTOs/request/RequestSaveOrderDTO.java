package com.ex.caffeine_bliss.DTOs.request;

import com.ex.caffeine_bliss.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSaveOrderDTO {
    private UUID customer;
    private UUID cashier;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private List<RequestSaveOrderItemsDTO> orderItems;
}
