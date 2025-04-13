package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.LoginDTO;
import com.ex.caffeine_bliss.DTOs.response.JwtResponse;
import com.ex.caffeine_bliss.security.JwtUtil;
import com.ex.caffeine_bliss.security.UserDetailsAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        UserDetailsAdapter userDetails = (UserDetailsAdapter) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getRole());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
