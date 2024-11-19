package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    30/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CropRepo extends JpaRepository<Crop,String> {

    Crop findByCropCode(String id);

    Optional<Crop> findFirstByOrderByCropCodeDesc();
    @Query(value = "SELECT c.crop_code FROM crops c ORDER BY c.crop_code DESC",nativeQuery = true)
    String findLatestCropCode();
}
