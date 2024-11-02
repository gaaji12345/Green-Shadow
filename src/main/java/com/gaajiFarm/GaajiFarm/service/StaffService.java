package com.gaajiFarm.GaajiFarm.service;/*  gaajiCode
    99
    02/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.StaffDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public interface StaffService {
    public List<StaffDTO> getAllCrops() ;


    public StaffDTO saveStaff(StaffDTO sDTO) ;


    public void updateStaff(StaffDTO c) ;

    public void deleteStaff(String id) ;


    public StaffDTO searchStaff(String id) ;


    public String genarateNextStaffCode() ;
}
