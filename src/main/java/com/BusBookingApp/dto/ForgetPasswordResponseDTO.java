package com.BusBookingApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForgetPasswordResponseDTO {
    private String Email;
    private String Message;
}
