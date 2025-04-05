package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO addCashier(UserDTO userDTO);

    UserDTO addAdminUser(UserDTO userDTO);

    UserDTO updateCashier(UserDTO userDTO);

    List<UserDTO> getAllCashiers();

    List<UserDTO> getAllAdmins();

    List<UserDTO> getAllOldEmployees();

    String deactivateUser(UUID id);

    UserDTO getUserById(UUID id);

    UserDTO getUserByEmail(UUID id);

}
