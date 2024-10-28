package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    26/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);





}
