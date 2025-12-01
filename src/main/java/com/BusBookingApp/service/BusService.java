package com.BusBookingApp.service;


import com.BusBookingApp.dto.CreateBusRequest;
import com.BusBookingApp.entity.Bus;
import com.BusBookingApp.entity.BusRoute;
import com.BusBookingApp.entity.User;
import com.BusBookingApp.repo.BusRepository;
import com.BusBookingApp.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Bus createBus(CreateBusRequest request){

        User owner=userRepository.findByUserId(request.getOwnerId())
                .orElseThrow(()->new RuntimeException("Owner not found"));
        User driver=userRepository.findByUserId(request.getDriverId())
                .orElseThrow(()->new RuntimeException("Driver not found"));

        //Create Bus
        Bus bus=Bus.builder()
                .owner(owner)
                .driver(driver)
                .busName(request.getBusName())
                .source(request.getSource())
                .destination(request.getDestination())
                .price(request.getPrice())
                .isActive(true)
                .build();

        //Map Route Stops
        List<BusRoute> busRoutes=request.getRouteStops().stream()
                .map(rs->BusRoute.builder()
                .bus(bus)
                .stopName(rs.getStopName())
                .stopOrder(rs.getStopOrder())
                .arrivalTime(rs.getArrivalTime())
                .departureTime(rs.getDepartureTime())
                .build())
                .toList();
        bus.setRouteStops(busRoutes);

        return busRepository.save(bus);
    }


}
