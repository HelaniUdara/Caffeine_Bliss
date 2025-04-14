package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.LoginDTO;
import com.ex.caffeine_bliss.DTOs.ResetPasswordFromTokenDTO;
import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestForgotPasswordDTO;
import com.ex.caffeine_bliss.DTOs.response.JwtResponse;
import com.ex.caffeine_bliss.security.JwtUtil;
import com.ex.caffeine_bliss.security.PasswordResetTokenUtil;
import com.ex.caffeine_bliss.security.UserDetailsAdapter;
import com.ex.caffeine_bliss.services.EmailService;
import com.ex.caffeine_bliss.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private PasswordResetTokenUtil passwordResetTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        UserDetailsAdapter userDetails = (UserDetailsAdapter) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getRole());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody RequestForgotPasswordDTO request) {
        UserDTO user = userService.getUserByEmail(request.getEmail());
        String token = passwordResetTokenUtil.generateResetToken(user.getEmail());
        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        emailService.sendResetPasswordEmail(user.getEmail(), resetLink);
        return ResponseEntity.ok("Password reset link has been sent to your email.");
    }

    @PostMapping("/reset-password-from-link")
    public ResponseEntity<String> resetPasswordFromToken(@RequestBody ResetPasswordFromTokenDTO request) {
        if (!passwordResetTokenUtil.validateResetToken(request.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
        String email = passwordResetTokenUtil.getEmailFromResetToken(request.getToken());
        String message = userService.resetPasswordFromLink(email, request.getNewPassword());
        return ResponseEntity.ok(message);
    }
}
