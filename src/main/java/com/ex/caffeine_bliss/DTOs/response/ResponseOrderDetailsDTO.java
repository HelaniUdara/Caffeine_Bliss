package com.ex.caffeine_bliss.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDetailsDTO {
    private UUID orderId;
    private Date createdAt;
    private String customerName;
    private String customerMobile;
    private double totalAmount;
    private List<ResponseOrderItemDTO> items;
}
