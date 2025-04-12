package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddUserDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestResetPasswordDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateUserDTO;
import com.ex.caffeine_bliss.entities.enums.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {
    String addUser(RequestAddUserDTO userDTO);

    UserDTO updateUser(RequestUpdateUserDTO userDTO);

    List<UserDTO> getAllUsers();

    PaginatedResponse<UserDTO> getLimitedUsers(boolean active, int page, int limit);

    List<UserDTO> getUsersByRole(UserRole role);

    List<UserDTO> getAllOldUsers();

    String deactivateUser(UUID id);

    UserDTO getUserById(UUID id);

    UserDTO getUserByEmail(String email);

    String resetPassword(RequestResetPasswordDTO dto);
}
