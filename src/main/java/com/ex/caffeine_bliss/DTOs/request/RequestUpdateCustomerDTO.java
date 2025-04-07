package com.ex.caffeine_bliss.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateCustomerDTO {
    private UUID id;
    private String name;
    private String mobileNo;
    private String email;
}
