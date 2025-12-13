package com.BusBookingApp.controller;


import com.BusBookingApp.dto.BlockSeatRequestDTO;
import com.BusBookingApp.dto.BusSearchResponse;
import com.BusBookingApp.dto.CreateBusRequest;
import com.BusBookingApp.entity.Bus;
import com.BusBookingApp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BusService busService;

    @PostMapping("/api/buses/create")
    public ResponseEntity<?> createBus(@RequestBody CreateBusRequest request){
        Bus bus=busService.createBus(request);
        return ResponseEntity.ok(bus);
    }

    @GetMapping("/api/buses/search")
    public ResponseEntity<?> searchBuses(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate journeyDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size
    ) {

            Page<BusSearchResponse> buses = busService.searchBuses(source, destination, journeyDate, page, size);

        return ResponseEntity.ok(buses);
    }

    @PostMapping("/seats/block")
    public ResponseEntity<?> blockSeats(@RequestBody BlockSeatRequestDTO request){


    }


}
