package com.BusBookingApp.repo;

import com.BusBookingApp.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    List<Bus> searchBuses(@Param("source") String source, @Param("destination") String destination);
}
