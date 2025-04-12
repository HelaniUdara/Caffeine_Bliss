package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddUserDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestResetPasswordDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateUserDTO;
import com.ex.caffeine_bliss.entities.enums.UserRole;
import com.ex.caffeine_bliss.services.UserService;
import com.ex.caffeine_bliss.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/getById", params = "id")
    public ResponseEntity<StandardResponse> getUserById(@RequestParam(value = "id") UUID id){
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        userDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/getByEmail", params = "email")
    public ResponseEntity<StandardResponse> getUserByEmail(@RequestParam(value = "email") String email){
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        userDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        users), HttpStatus.OK);
    }

    @GetMapping(path = "/getLimitedUsers", params = {"page", "limit"})
    public ResponseEntity<StandardResponse> getLimitedUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        if(limit > 20){ limit = 20; }
        PaginatedResponse<UserDTO> userList = userService.getLimitedUsers(true, page, limit);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        userList), HttpStatus.OK);
    }

    @GetMapping(path = "/getUsersByRole", params = "role")
    public ResponseEntity<StandardResponse> getUsersByRole(@RequestParam(value = "role") UserRole role){
        List<UserDTO> users = userService.getUsersByRole(role);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        users), HttpStatus.OK);
    }

    @GetMapping(path = "/getOldUsers")
    public ResponseEntity<StandardResponse> getOldUsers(){
        List<UserDTO> users = userService.getAllOldUsers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        users), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> createUser(@RequestBody RequestAddUserDTO userDTO){
        String message = userService.addUser(userDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS",
                        message), HttpStatus.CREATED);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody RequestUpdateUserDTO userDTO){
        UserDTO user = userService.updateUser(userDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        user), HttpStatus.OK);
    }

    @PostMapping(path = "/deactivate", params = "id")
    public ResponseEntity<StandardResponse> deactivateUser(@RequestParam(value = "id") UUID id){
        String message = userService.deactivateUser(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        message), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<StandardResponse> resetPassword(@RequestBody RequestResetPasswordDTO dto) {
        String message = userService.resetPassword(dto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        message), HttpStatus.OK);
    }

}
