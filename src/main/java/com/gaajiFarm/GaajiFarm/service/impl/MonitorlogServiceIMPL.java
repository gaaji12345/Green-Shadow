package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    14/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.CropDetailDTO;
import com.gaajiFarm.GaajiFarm.dtos.MonitorlogDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.*;
import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import com.gaajiFarm.GaajiFarm.util.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorlogServiceIMPL {

    @Autowired
    CropRepo cropRepo;

    @Autowired
    FieldRepo fieldRepo;

    @Autowired
    private MonitorLogRepo monitoringLogRepository;



    @Autowired
    private StaffRepo staffRepository;

    @Autowired
    private CropDetailRepo cropDetailRepo;



//    @Transactional
//    public void updateMonitoringLog(MonitorlogDTO monitorlogDTO) {
//        // Find or create the MonitoringLogService entity
//        MonitoringLogService logService = monitoringLogRepository.findById(monitorlogDTO.getLogCode())
//                .orElseThrow(() -> new IllegalArgumentException("MonitoringLogService not found"));
//
//        // Iterate through each CropDetailDTO to update the crop quantity and staff quantity
//        for (CropDetailDTO cropDetailDTO : monitorlogDTO.getCropDetails()) {
//            // Find Crop and Staff entities by their codes
//            Crop crop = cropRepo.findById(cropDetailDTO.getCropCode())
//                    .orElseThrow(() -> new IllegalArgumentException("Crop not found"));
//
//            Staff staff = staffRepository.findById(cropDetailDTO.getStaffId())
//                    .orElseThrow(() -> new IllegalArgumentException("Staff not found"));
//
//            // Update the crop quantity (decrease it)
//            int updatedCropQuantity = crop.getQty() - cropDetailDTO.getQuantity();
//            if (updatedCropQuantity < 0) {
//                throw new IllegalArgumentException("Not enough crop quantity available");
//            }
//            crop.setQty(updatedCropQuantity);
//
//            // Update the staff quantity (decrease the number of members)
//            int updatedStaffMembers = staff.getMembers() - cropDetailDTO.getMembersInStaff();
//            if (updatedStaffMembers < 0) {
//                throw new IllegalArgumentException("Not enough staff members available");
//            }
//            staff.setMembers(updatedStaffMembers);
//
//            // Save updated entities back to the database
//            cropRepo.save(crop);
//            staffRepository.save(staff);
//
//            // Optionally, if you want to track these updates, you can create a CropDetails record
//            CropDetails cropDetails = new CropDetails();
//            cropDetails.setLogCode(cropDetailDTO.getLogCode());
//            cropDetails.setCrop_code(cropDetailDTO.getCropCode());
//            cropDetails.setStaff_id(cropDetailDTO.getStaffId());
//            cropDetails.setQuantity(cropDetailDTO.getQuantity());
//            cropDetails.setMembersInStaff(cropDetailDTO.getMembersInStaff());
//
//            // Set the log service reference
//            cropDetails.setLogService(logService);
//
//            // Save the cropDetails entry to the database (you can also persist this to track the change)
//            logService.getCropDetails().add(cropDetails);
//        }
//
//        // Save the updated MonitoringLogService (if required)
//        monitoringLogRepository.save(logService);
//    }




    public List<MonitorlogDTO> getAllMonitoringLogs() {
        // Fetch all MonitoringLogService records from the repository
        List<MonitoringLogService> logs = monitoringLogRepository.findAll();

        // Convert the list of entities to DTOs
        return logs.stream().map(log -> {
            // Create the DTO
            MonitorlogDTO dto = new MonitorlogDTO();
            dto.setLogCode(log.getLogCode());
            dto.setLogDate(LocalDate.parse(log.getLogDate().toString()));  // Assuming logDate is a Timestamp or LocalDateTime
            dto.setLogDetails(log.getLogDetails());
            dto.setRole(log.getRole().name());  // Convert role to a string if it's an enum
            dto.setFieldCode(log.getField().getFieldCode());  // Assuming Field entity has a fieldCode attribute

            // Map CropDetails to DTOs
            List<CropDetailDTO> cropDetailsDTOs = log.getCropDetails().stream().map(cropDetail -> {
                CropDetailDTO cropDetailDTO = new CropDetailDTO();
                cropDetailDTO.setLogCode(cropDetail.getLogCode());
                cropDetailDTO.setCropCode(cropDetail.getCrop_code());
                cropDetailDTO.setStaffId(cropDetail.getStaff_id());
                cropDetailDTO.setQuantity(cropDetail.getQuantity());
                cropDetailDTO.setMembersInStaff(cropDetail.getMembersInStaff());
                return cropDetailDTO;
            }).collect(Collectors.toList());

            dto.setCropDetails(cropDetailsDTOs);  // Set the list of CropDetailDTOs

            return dto;
        }).collect(Collectors.toList());
    }


    @Transactional
    public void updateMonitoringLog(MonitorlogDTO monitorlogDTO) {
        // If logCode is not provided, generate a new one
        if (monitorlogDTO.getLogCode() == null || monitorlogDTO.getLogCode().isEmpty()) {
            monitorlogDTO.setLogCode(nextCode("LOG-"));
        }

        // Fetch the Field entity using the fieldCode
        Field field = fieldRepo.findById(monitorlogDTO.getFieldCode())
                .orElseThrow(() -> new IllegalArgumentException("Field not found"));

        // Check if the MonitoringLogService entity already exists; if not, create a new one
        MonitoringLogService logService = monitoringLogRepository.findById(monitorlogDTO.getLogCode())
                .orElseGet(() -> {
                    // Create a new logService if it does not exist
                    MonitoringLogService newLogService = new MonitoringLogService();
                    newLogService.setLogCode(monitorlogDTO.getLogCode());
                    newLogService.setLogDate(monitorlogDTO.getLogDate());
                    newLogService.setLogDetails(monitorlogDTO.getLogDetails());
                    newLogService.setRole(UserRole.valueOf(monitorlogDTO.getRole()));
                    newLogService.setField(field);  // Set the Field object here
                    // Initialize the cropDetails list if it's null
                    newLogService.setCropDetails(new ArrayList<>());
                    // Save the new MonitoringLogService entity
                    monitoringLogRepository.save(newLogService);
                    return newLogService;
                });

        // Iterate through each CropDetailDTO to update the crop quantity and staff quantity
        for (CropDetailDTO cropDetailDTO : monitorlogDTO.getCropDetails()) {
            // Find Crop and Staff entities by their codes
            Crop crop = cropRepo.findById(cropDetailDTO.getCropCode())
                    .orElseThrow(() -> new IllegalArgumentException("Crop not found"));

            Staff staff = staffRepository.findById(cropDetailDTO.getStaffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found"));

            // Update the crop quantity (decrease it)
            int updatedCropQuantity = crop.getQty() - cropDetailDTO.getQuantity();
            if (updatedCropQuantity < 0) {
                throw new IllegalArgumentException("Not enough crop quantity available");
            }
            crop.setQty(updatedCropQuantity);

            // Update the staff quantity (decrease the number of members)
            int updatedStaffMembers = staff.getMembers() - cropDetailDTO.getMembersInStaff();
            if (updatedStaffMembers < 0) {
                throw new IllegalArgumentException("Not enough staff members available");
            }
            staff.setMembers(updatedStaffMembers);

            // Save updated entities back to the database
            cropRepo.save(crop);
            staffRepository.save(staff);

            // Optionally, create a new CropDetails record
            CropDetails cropDetails = new CropDetails();
            cropDetails.setLogCode(logService.getLogCode());  // Set logCode from logService
            cropDetails.setCrop_code(cropDetailDTO.getCropCode());
            cropDetails.setStaff_id(cropDetailDTO.getStaffId());
            cropDetails.setQuantity(cropDetailDTO.getQuantity());
            cropDetails.setMembersInStaff(cropDetailDTO.getMembersInStaff());

            // Set the log service reference
            cropDetails.setLogService(logService);

            // Save the cropDetails entry to the database
            cropDetailRepo.save(cropDetails);  // Assuming you have a cropDetailsRepo

            // Add CropDetails to the MonitoringLogService entity
            logService.getCropDetails().add(cropDetails);
        }

        // Save the updated MonitoringLogService (this ensures the logCode and related data are saved)
        monitoringLogRepository.save(logService);  // Save the MonitoringLogService to the DB
    }







    public String nextCode(String prefix) {
        // Get the count of inventory items
        long count = monitoringLogRepository.count(); // This gets the total number of rows

        // Generate the next inventory code
        String nextInventoryCode = prefix + String.format("%03d", count + 1);
        return nextInventoryCode;
    }

}
