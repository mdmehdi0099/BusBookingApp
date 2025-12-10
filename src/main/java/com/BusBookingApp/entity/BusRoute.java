package com.BusBookingApp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="bus_routes")
@EntityListeners(AuditingEntityListener.class)
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    @ManyToOne
    @JoinColumn(name = "bus_id",nullable = false)
    @JsonBackReference
    private Bus bus;

    private String stopName;

    private int stopOrder;//sequence wise

    private LocalTime arrivalTime;
    private LocalTime departureTime;

}
