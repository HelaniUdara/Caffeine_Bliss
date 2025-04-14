package com.ex.caffeine_bliss.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordFromTokenDTO {
    private String token;
    private String newPassword;
}
