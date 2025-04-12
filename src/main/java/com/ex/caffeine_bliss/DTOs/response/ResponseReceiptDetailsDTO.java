package com.ex.caffeine_bliss.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReceiptDetailsDTO {
    private UUID receiptId;
    private UUID orderId;
    private String customerEmail;
    private Date sentAt;
}
