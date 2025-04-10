package com.ex.caffeine_bliss.DTOs.request;

import com.ex.caffeine_bliss.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private boolean active;
}
