package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    05/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.EqDTO;
import com.gaajiFarm.GaajiFarm.dtos.VechielDTO;
import com.gaajiFarm.GaajiFarm.service.EqService;
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
public class EqServiceIMPL  implements EqService {

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
                .map(v -> {
                    // Map basic fields using your mapper
                    EqDTO eqDTO = mapper.map(v, EqDTO.class);

                    // Explicitly set equantity to avoid potential mapping issues
                    eqDTO.setEquantity(v.getEquntity());

                    return eqDTO;
                })
                .collect(Collectors.toList());
    }






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



    public void updateEq(EqDTO c) {

       Equpment map = mapper.map(c, Equpment.class);
        eqRepo.save(map);

    }


    public void deleteEq(String sid) {
        eqRepo.deleteById(sid);
    }



    public String genarateNextEcode() {
        String lastCustomerCode = eqRepo.findLatestEqId();
        if(lastCustomerCode==null){lastCustomerCode = "EQ00";}
        int numericPart = Integer.parseInt(lastCustomerCode.substring(3));
        numericPart++;
        String nextSupplierCode = "EQ-" + String.format("%03d", numericPart);
        return nextSupplierCode;
    }


}
