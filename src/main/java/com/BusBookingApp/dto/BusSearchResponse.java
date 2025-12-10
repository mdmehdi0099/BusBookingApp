package com.BusBookingApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BusSearchResponse {
    private Long busId;
    private String busName;

    private Integer totalSeatCapacity;
    private Integer ladiesSeatQuota;

    private String source;
    private String destination;
    private double price;

    private String driverName;
    private String driverPhone;

    private List<RouteStopInfo> routeStops;

    @Data
    @Builder
    public static class RouteStopInfo{
            private String stopName;
            private int stopOrder;
            private LocalTime arrivalTime;
            private LocalTime departureTime;
    }

}
