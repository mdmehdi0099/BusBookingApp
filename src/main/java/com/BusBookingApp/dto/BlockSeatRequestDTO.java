package com.BusBookingApp.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlockSeatRequestDTO {
        private Long busId;
        private LocalDate journeyDate;
        private List<Integer> seatNumbers;
        private Long userId;
}
