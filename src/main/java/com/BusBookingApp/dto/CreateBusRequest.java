package com.BusBookingApp.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Data
@Getter
@Setter
public class CreateBusRequest {
    private Long ownerId;
    private Long driverId;
    private String busName;
    private String source;
    private String destination;
    private double price;

    private List<RouteStopRequest> routeStops;

    @Data
    public static class RouteStopRequest{
        private String stopName;
        private int stopOrder;
        private LocalTime arrivalTime;
        private LocalTime departureTime;
    }


}
