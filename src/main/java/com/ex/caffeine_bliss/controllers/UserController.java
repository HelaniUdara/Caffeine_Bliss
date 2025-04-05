package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/user")
public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/addUser")
//    public UserDTO addUser(@RequestBody UserDTO userDTO){
//        return userService.addUser(userDTO);
//    }
//
//    @GetMapping(path = "/getUser", params = "email")
//    public UserDTO getUserByEmail(String email){
//        return userService.getUserByEmail(email);
//    }
//
//    @GetMapping(path = "/getAllUsers", params = "email")
//    public List<UserDTO> getAllUsers(String email){
//        return userService.getAllUsers();
//    }
}
