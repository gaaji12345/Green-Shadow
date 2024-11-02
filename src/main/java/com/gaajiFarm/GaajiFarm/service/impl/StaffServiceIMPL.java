package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    01/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.CropDTO;
import com.gaajiFarm.GaajiFarm.dtos.StaffDTO;
import com.gaajiFarm.GaajiFarm.service.StaffService;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import com.gaajiFarm.GaajiFarm.util.repo.StaffRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceIMPL  implements StaffService {
    @Autowired
    ModelMapper mapper;

    @Autowired
    StaffRepo staffRepo;

    public List<StaffDTO> getAllCrops() {
        return staffRepo.findAll().stream()
                .map(crop -> mapper.map(crop, StaffDTO.class))
                .collect(Collectors.toList());
    }


    public StaffDTO saveStaff(StaffDTO sDTO) {
        if(staffRepo.existsByStaffId(sDTO.getStaffId())){
            throw new NotFoundException("This StaffId "+sDTO.getStaffId()+" already exicts...");
        }
        sDTO.setStaffId(genarateNextStaffCode());
        return mapper.map(staffRepo.save(mapper.map(
                sDTO, Staff.class)), StaffDTO.class
        );
    }


    public void updateStaff(StaffDTO c) {

       Staff map = mapper.map(c, Staff.class);
        staffRepo.save(map);

    }

    public void deleteStaff(String id) {

        if(!staffRepo.existsByStaffId(id)){
            throw  new NotFoundException("Staff ID"+ id + "Not Found...");
        }
        staffRepo.deleteByStaffId(id);
    }


    public StaffDTO searchStaff(String id) {

        if(!staffRepo.existsByStaffId(id)){
            throw new NotFoundException("Staff Id "+id+" Not Found!");
        }
        return mapper.map(staffRepo.findByStaffId(id), StaffDTO.class);
    }


    public String genarateNextStaffCode() {
        String lastCustomerCode = staffRepo.findLatestStaffId();
        if(lastCustomerCode==null){lastCustomerCode = "SF00";}
        int numericPart = Integer.parseInt(lastCustomerCode.substring(3));
        numericPart++;
        String nextSupplierCode = "SF" + String.format("%03d", numericPart);
        return nextSupplierCode;
    }




}
