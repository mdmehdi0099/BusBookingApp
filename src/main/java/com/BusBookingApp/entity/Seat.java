package com.BusBookingApp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long busId;
    private LocalDate journeyDate;
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private LocalDateTime blockedAt;


}
