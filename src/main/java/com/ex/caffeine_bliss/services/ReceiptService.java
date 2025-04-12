package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.ReceiptDTO;
import com.ex.caffeine_bliss.DTOs.response.ResponseReceiptDetailsDTO;

import java.util.UUID;

public interface ReceiptService {
    ReceiptDTO sentReceiptEmail(UUID orderId);

    ResponseReceiptDetailsDTO getReceiptById(UUID id);

    ResponseReceiptDetailsDTO getReceiptByOrderId(UUID orderId);
}
