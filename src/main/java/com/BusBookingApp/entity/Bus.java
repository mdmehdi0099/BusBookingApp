package com.BusBookingApp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL,orphanRemoval = true)
    @OrderBy("stopOrder ASC")
    private List<BusRoute> routeStops=new ArrayList<>();

    private String busName;
    private String source;
    private String destination;
    private double price;
    private String other;

    private boolean isActive = true;
    private String deactivationReason;
    private LocalDateTime deactivatedAt;

    private String reActivationReason;
    private LocalDateTime reActivatedAt;

    private Integer otp;

    @CreatedDate
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;





}
