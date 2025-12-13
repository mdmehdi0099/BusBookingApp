package com.BusBookingApp.service;


import com.BusBookingApp.dto.BusSearchResponse;
import com.BusBookingApp.dto.CreateBusRequest;
import com.BusBookingApp.entity.Bus;
import com.BusBookingApp.entity.BusRoute;
import com.BusBookingApp.entity.User;
import com.BusBookingApp.repo.BusRepository;
import com.BusBookingApp.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (request.getLadiesSeatQuota()> request.getTotalSeatCapacity()){
            throw new RuntimeException("Total Ladies seat cannot be greater than the Total seat Capacity");
        }
        //Create Bus
        Bus bus=Bus.builder()
                .owner(owner)
                .driver(driver)
                .busName(request.getBusName())
                .ladiesSeatQuota(request.getLadiesSeatQuota())
                .totalSeatCapacity(request.getTotalSeatCapacity())
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

    public Page<BusSearchResponse> searchBuses(String source, String destination, LocalDate journeyDate, int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("busName").ascending());

        Page<Bus> buses = busRepository.searchBuses(source, destination, pageable);

        return buses.map(bus ->
                BusSearchResponse.builder()
                        .busId(bus.getBusId())
                        .busName(bus.getBusName())
                        .ladiesSeatQuota(bus.getLadiesSeatQuota())
                        .totalSeatCapacity(bus.getTotalSeatCapacity())
                        .source(bus.getSource())
                        .destination(bus.getDestination())
                        .price(bus.getPrice())
                        .driverName(bus.getDriver().getUsername())
                        .driverPhone(bus.getDriver().getPhone())
                        .routeStops(
                                bus.getRouteStops().stream()
                                        .map(rs -> BusSearchResponse.RouteStopInfo.builder()
                                                .stopName(rs.getStopName())
                                                .stopOrder(rs.getStopOrder())
                                                .arrivalTime(rs.getArrivalTime())
                                                .departureTime(rs.getDepartureTime())
                                                .build())
                                        .toList()
                        )
                        .build()
        );
    }


}
