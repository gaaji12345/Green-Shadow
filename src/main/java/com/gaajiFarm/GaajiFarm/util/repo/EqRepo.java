package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    05/11/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.Equpment;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EqRepo extends JpaRepository<Equpment,String> {
    Staff findByEquipmentId(String eqId); // Assuming staffId is a String


    Boolean existsByEquipmentId(String id);

    @Query(value = "SELECT equipment_id FROM equpment ORDER BY equipment_id DESC LIMIT 1", nativeQuery = true)
    String findLatestEqId();
}
