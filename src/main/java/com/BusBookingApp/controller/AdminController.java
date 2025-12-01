package com.BusBookingApp.controller;


import com.BusBookingApp.dto.CreateBusRequest;
import com.BusBookingApp.entity.Bus;
import com.BusBookingApp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BusService busService;


    public ResponseEntity<?> createBus(@RequestBody CreateBusRequest request){
        Bus bus=busService.createBus(request);
        return ResponseEntity.ok(bus);
    }


}
