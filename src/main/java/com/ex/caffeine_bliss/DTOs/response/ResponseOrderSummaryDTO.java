package com.ex.caffeine_bliss.DTOs.response;

import com.ex.caffeine_bliss.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderSummaryDTO {
    private UUID id;
    private Date createdAt;
    private PaymentMethod paymentMethod;
    private double totalPrice;
    private String customerName;
    private String cashierName;
}
