package com.BusBookingApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordResponseDTO {
    private String email;
    private Integer otp;
    private String message;
}
