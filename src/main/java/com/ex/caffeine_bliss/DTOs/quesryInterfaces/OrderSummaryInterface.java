package com.ex.caffeine_bliss.DTOs.quesryInterfaces;

import com.ex.caffeine_bliss.entities.enums.PaymentMethod;

import java.util.Date;

public interface OrderSummaryInterface {
    String getId();
    Date getCreatedAt();
    PaymentMethod getPaymentMethod();
    double getTotalPrice();
    String getCustomerName();
    String getCashierName();
}
