package com.ex.caffeine_bliss.DTOs;

import com.ex.caffeine_bliss.DTOs.response.ResponseOrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptEmailDTO {
    private String receiptId;
    private String orderId;
    private String customerName;
    private String customerMobile;
    private String createdAt;
    private double totalAmount;
    private List<ResponseOrderItemDTO> items;
}
