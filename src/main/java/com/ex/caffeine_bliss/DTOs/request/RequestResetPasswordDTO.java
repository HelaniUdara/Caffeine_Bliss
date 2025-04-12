package com.ex.caffeine_bliss.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResetPasswordDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
}
