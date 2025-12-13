package com.BusBookingApp.service;

import com.BusBookingApp.dto.BlockSeatRequestDTO;
import com.BusBookingApp.dto.BlockSeatResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SeatService {

    public BlockSeatResponseDTO blockSeats(BlockSeatRequestDTO requestDTO){

        //get the time
        LocalDateTime time=LocalDateTime.now();
        //create a new array list for storing the list of the blocked seat

        //go through the list of the seat number and check



    }
}
