package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    03/11/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.enitity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VechicleRepo extends JpaRepository<Vehicle,String> {

    Staff findByVehicleCode(String staffId); // Assuming staffId is a String


    Boolean existsByVehicleCode(String id);
    void deleteByVehicleCode(String id);
    @Query(value = "SELECT vehicle_code FROM vehicle ORDER BY vehicle_code DESC LIMIT 1", nativeQuery = true)
    String findLatestVehicleCode();

}
