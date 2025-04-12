package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.ReceiptDTO;

import java.util.UUID;

public interface ReceiptService {
    ReceiptDTO sentReceiptEmail(UUID orderId);

    ReceiptDTO getReceiptById(UUID id);

    ReceiptDTO getReceiptByOrderId(UUID orderId);
}
