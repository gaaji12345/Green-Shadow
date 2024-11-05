package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    05/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.EqDTO;
import com.gaajiFarm.GaajiFarm.dtos.VechielDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.Equpment;
import com.gaajiFarm.GaajiFarm.util.enitity.Field;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.enitity.Vehicle;
import com.gaajiFarm.GaajiFarm.util.enums.EqupmentTypes;
import com.gaajiFarm.GaajiFarm.util.enums.Status;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import com.gaajiFarm.GaajiFarm.util.repo.EqRepo;
import com.gaajiFarm.GaajiFarm.util.repo.FieldRepo;
import com.gaajiFarm.GaajiFarm.util.repo.StaffRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EqServiceIMPL {

  @Autowired
    EqRepo eqRepo;
  @Autowired
    FieldRepo frepo;
  @Autowired
    StaffRepo staffRepo;

  @Autowired
    ModelMapper mapper;


    public List<EqDTO> getAllEq() {
        return eqRepo.findAll().stream()
                .map(v -> mapper.map(v, EqDTO.class))
                .collect(Collectors.toList());
    }


//    public EqDTO saveEq(EqDTO eDTO) {
//        // Check if equipment ID already exists
//        if (eqRepo.existsByEquipmentId(eDTO.getEquipmentId())) {
//            throw new NotFoundException("This equipment ID " + eDTO.getEquipmentId() + " already exists.");
//        }
//
//        // Create new Equipment entity
//        Equpment equipment = new Equpment();
//
//        // Set equipment fields from DTO
//        equipment.setName(eDTO.getName());
//        equipment.setType(EqupmentTypes.valueOf(eDTO.getType()));
//        equipment.setStatus(Status.valueOf(eDTO.getStatus()));
//        equipment.setEquntity(eDTO.getQuantity());
//
//        // Set assigned field if available
//        Field field = frepo.findByFieldCode(eDTO.getAssignedFieldCode()).orElse(null);
//        if (field != null) {
//            equipment.setAssignedField(field);
//        }
//
//        // Set assigned staff if available
////        Staff staff = staffRepo.findByStaffId(eDTO.getAssignedStaffId()).orElse(null);
////        if (staff != null) {
////            equipment.setAssignedStaff(staff);
////        }
//
//        Staff staff = staffRepo.findByStaffId(eDTO.getAssignedStaffId());
//        if (staff != null) {
//            equipment.setAssignedStaff(staff);
//        }
//
//        // Generate and set new equipment ID
//        eDTO.setEquipmentId(genarateNextEcode());
//        equipment.setEquipmentId(eDTO.getEquipmentId());
//
//        // Save and map back to DTO
//        Equpment savedEquipment = eqRepo.save(equipment);
//        return mapper.map(savedEquipment, EqDTO.class);
//    }


    public EqDTO saveEq(EqDTO eDTO) {
        // Check if equipment ID already exists
        if (eqRepo.existsByEquipmentId(eDTO.getEquipmentId())) {
            throw new NotFoundException("This equipment ID " + eDTO.getEquipmentId() + " already exists.");
        }

        // Map fields from DTO to entity
        Equpment equipment = new Equpment();
        equipment.setName(eDTO.getName());
        equipment.setType(EqupmentTypes.valueOf(eDTO.getType()));
        equipment.setStatus(Status.valueOf(eDTO.getStatus()));
        equipment.setEquntity(eDTO.getEquantity()); // Set quantity field

        // Set assigned field
        Field field = frepo.findByFieldCode(eDTO.getAssignedFieldCode()).orElse(null);
        if (field != null) {
            equipment.setAssignedField(field);
        }

        // Set assigned staff
//        Staff staff = staffRepo.findByStaffId(eDTO.getAssignedStaffId()).orElse(null);
//        if (staff != null) {
//            equipment.setAssignedStaff(staff);
//        }

                Staff staff = staffRepo.findByStaffId(eDTO.getAssignedStaffId());
        if (staff != null) {
            equipment.setAssignedStaff(staff);
        }

        // Generate new equipment ID
        eDTO.setEquipmentId(genarateNextEcode());
        equipment.setEquipmentId(eDTO.getEquipmentId());

        // Save and map to DTO
        Equpment savedEquipment = eqRepo.save(equipment);
        return mapper.map(savedEquipment, EqDTO.class);
    }




//    public EqDTO saveEq(EqDTO eDTO) {
//        Equpment equpment=new Equpment();
//
//        if(eqRepo.existsByEquipmentId(eDTO.getEquipmentId())){
//            throw new NotFoundException("This eId "+eDTO.getEquipmentId()+" already exicts...");
//        }
//
//        Field field = frepo.findByFieldCode(eDTO.getAssignedFieldCode()).orElse(null);
//        if (field != null) {
//            equpment.setAssignedField(field);
//        }
//
//
//        Staff staff = staffRepo.findByStaffId(eDTO.getAssignedStaffId());
//        if (staff != null) {
//            equpment.setAssignedStaff(staff);
//        }
//
//        eDTO.setEquipmentId(genarateNextEcode());
//        return mapper.map(eqRepo.save(mapper.map(
//                eDTO, Equpment.class)), EqDTO.class
//        );
//
//    }

    private String genarateNextEcode() {
        String lastCustomerCode = eqRepo.findLatestEqId();
        if(lastCustomerCode==null){lastCustomerCode = "EQ00";}
        int numericPart = Integer.parseInt(lastCustomerCode.substring(3));
        numericPart++;
        String nextSupplierCode = "EQ-" + String.format("%03d", numericPart);
        return nextSupplierCode;
    }


}
