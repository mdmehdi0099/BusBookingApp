package com.BusBookingApp.service;


import com.BusBookingApp.repo.BusRepository;
import com.BusBookingApp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;


}
