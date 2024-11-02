package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    30/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StaffRepo extends JpaRepository<Staff,String> {

//    Staff findByStaff_id(String id);

   Staff findByStaffId(String staffId); // Assuming staffId is a String


   Boolean existsByStaffId(String id);
   void deleteByStaffId(String id);
   @Query(value = "SELECT staff_id FROM staff ORDER BY staff_id DESC LIMIT 1", nativeQuery = true)
   String findLatestStaffId();



}
