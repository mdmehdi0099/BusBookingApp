package com.BusBookingApp.repo;

import com.BusBookingApp.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
    @Query("""
            SELECT b FROM Bus b 
            WHERE LOWER(b.source) = LOWER(:source)
            AND LOWER(b.destination) = LOWER(:destination)
            AND b.isActive = true
           """)
    List<Bus> searchBuses(String source, String destination);
}
