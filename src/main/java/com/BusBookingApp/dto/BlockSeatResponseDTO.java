package com.BusBookingApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlockSeatResponseDTO {
    private Long busId;
    private String busName;

    private List<Integer> blockedSeatNumbers;
    private LocalDateTime blockedAt;
    private LocalDateTime expiresAt;

    private boolean stillValid;
    private String message;



}
