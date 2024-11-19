package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    30/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.Field;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FieldRepo extends JpaRepository<Field,String> {
    @Query("SELECT f.fieldCode FROM Field f ORDER BY f.fieldCode DESC")
    List<String> findLatestFieldCode(Pageable pageable);

    Optional<Field> findByFieldCode(String fieldCode);

}
