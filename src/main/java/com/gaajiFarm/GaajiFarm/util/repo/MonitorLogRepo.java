package com.gaajiFarm.GaajiFarm.util.repo;/*  gaajiCode
    99
    14/11/2024
    */

import com.gaajiFarm.GaajiFarm.util.enitity.MonitoringLogService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonitorLogRepo extends JpaRepository<MonitoringLogService,String> {

    Optional<MonitoringLogService> findByLogCode(String logCode);

    // Custom query to find the latest log code (assuming it's sorted lexicographically)
    Optional<MonitoringLogService> findTopByOrderByLogCodeDesc();
}
