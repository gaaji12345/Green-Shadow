package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    14/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.MonitorlogDTO;
import com.gaajiFarm.GaajiFarm.service.impl.MonitorlogServiceIMPL;
import com.gaajiFarm.GaajiFarm.util.enitity.Crop;
import com.gaajiFarm.GaajiFarm.util.enitity.MonitoringLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/log")
public class MonitorlogController {


    @Autowired
    MonitorlogServiceIMPL monitorlogServiceIMPL;
    @PostMapping("/update")
    public ResponseEntity<String> updateMonitoringLog(@RequestBody MonitorlogDTO monitorlogDTO) {
        try {
            monitorlogServiceIMPL.updateMonitoringLog(monitorlogDTO);
            return ResponseEntity.ok("Monitoring log updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<MonitorlogDTO>> getAllMonitoringLogs() {
        // Fetch all logs from the service layer
        List<MonitorlogDTO> logs = monitorlogServiceIMPL.getAllMonitoringLogs();

        // Return the list of MonitorlogDTOs wrapped in a ResponseEntity with an OK status
        return ResponseEntity.ok(logs);
    }
    

    @GetMapping("/most-used")
    public String getMostUsedCrop() {
        Crop mostUsedCrop = monitorlogServiceIMPL.findMostUsedCrop();
        if (mostUsedCrop == null) {
            throw new NullPointerException("Most used crop not found.");
        }
        return mostUsedCrop.getCropCode(); // Only returning cropCode
    }

}
