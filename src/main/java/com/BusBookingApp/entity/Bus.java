package com.BusBookingApp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
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
    @JsonManagedReference
    private List<BusRoute> routeStops=new ArrayList<>();
    @Column(nullable = false,unique = true)
    private String busName;
    @Column(nullable = false)
    private Integer totalSeatCapacity;
    @Column(nullable = false)
    private Integer ladiesSeatQuota;
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
