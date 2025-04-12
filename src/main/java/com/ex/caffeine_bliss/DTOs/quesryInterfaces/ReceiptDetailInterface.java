package com.ex.caffeine_bliss.DTOs.quesryInterfaces;

import java.util.Date;
import java.util.UUID;

public interface ReceiptDetailInterface {
    UUID getReceiptId();
    UUID getOrderId();
    String getCustomerEmail();
    Date getSentAt();
}
