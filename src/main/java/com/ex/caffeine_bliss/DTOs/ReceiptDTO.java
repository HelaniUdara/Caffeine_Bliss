package com.ex.caffeine_bliss.DTOs;

import com.ex.caffeine_bliss.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private UUID id;
    private Order order;
    private String email;
    private Date sentAt;
}
