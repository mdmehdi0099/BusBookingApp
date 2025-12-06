package com.BusBookingApp.dto;

import com.BusBookingApp.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String phone;
    private String password;
    private Role role;
    private String gender;
    private int age;
}
