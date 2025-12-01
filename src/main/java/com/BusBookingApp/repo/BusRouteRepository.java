package com.BusBookingApp.repo;


import com.BusBookingApp.entity.Bus;
import com.BusBookingApp.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute,Long> {


}
