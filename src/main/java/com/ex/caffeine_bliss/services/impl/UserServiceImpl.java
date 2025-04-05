package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl  implements UserService {


    @Override
    public UserDTO addCashier(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO addAdminUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO updateCashier(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> getAllCashiers() {
        return null;
    }

    @Override
    public List<UserDTO> getAllAdmins() {
        return null;
    }

    @Override
    public List<UserDTO> getAllOldEmployees() {
        return null;
    }

    @Override
    public String deactivateUser(UUID id) {
        return null;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(UUID id) {
        return null;
    }
}
