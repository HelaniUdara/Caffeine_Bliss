package com.ex.caffeine_bliss.DTOs;

import com.ex.caffeine_bliss.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
}
