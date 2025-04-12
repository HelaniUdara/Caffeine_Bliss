package com.ex.caffeine_bliss.DTOs.quesryInterfaces;

import java.util.Date;
import java.util.UUID;

public interface OrderDetailInterface {
    UUID getOrderId();
    Date getCreatedAt();
    double getTotalPrice();
    String getCustomerName();
    String getCustomerMobile();
    String getCustomerEmail();
}
