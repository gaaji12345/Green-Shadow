package com.gaajiFarm.GaajiFarm.util.repo;


import com.gaajiFarm.GaajiFarm.util.enitity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}
