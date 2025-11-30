package com.BusBookingApp.controller;

import com.BusBookingApp.dto.*;
import com.BusBookingApp.service.AuthService;
import com.BusBookingApp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Autowired
    private EmailService emailService;

    //register customer
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    //registerAdminUser
    @PostMapping("/registerAdminUser")
    public ResponseEntity<?> registerAdminUser(@RequestBody RegisterRequest request) {
        return authService.registerAdminUser(request);
    }

    //registerCounterUser
    @PostMapping("/registerCounterUser")
    public ResponseEntity<?> registerCounterUser(@RequestBody RegisterRequest request) {
        return authService.registerCounterUser(request);
    }

    //registerAgentUser
    @PostMapping("/registerAgentUser")
    public ResponseEntity<?> registerAgentUser(@RequestBody RegisterRequest request) {
        return authService.registerAgentUser(request);
    }

    //registerStaffUser
    @PostMapping("/registerStaffUser")
    public ResponseEntity<?> registerStaffUser(@RequestBody RegisterRequest request) {
        return authService.registerStaffUser(request);
    }

    //registerOwnerUser
    @PostMapping("/registerOwnerUser")
    public ResponseEntity<?> registerOwnerUser(@RequestBody RegisterRequest request) {
        return authService.registerOwnerUser(request);
    }

    //registerDriverUser
    @PostMapping("/registerDriverUser")
    public ResponseEntity<?> registerDriverUser(@RequestBody RegisterRequest request) {
        return authService.registerDriverUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<ForgetPasswordResponseDTO> forgetPassword(@RequestBody ForgetPasswordRequestDTO forgetPasswordRequestDTO) {
        String email = forgetPasswordRequestDTO.getEmail();
        ForgetPasswordResponseDTO response = emailService.sendForgetPasswordEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ResetPasswordResponseDTO> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        return emailService.resetPassword(resetPasswordRequestDTO);
    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
//        String h = request.getHeader("Authorization");
//        if (h == null || !h.startsWith("Bearer ")) return ResponseEntity.badRequest().body("Missing Authorization header");
//        String oldToken = h.substring(7);
//        AuthResponse res = authService.refresh(oldToken);
//        return ResponseEntity.ok(res);
//    }




}
