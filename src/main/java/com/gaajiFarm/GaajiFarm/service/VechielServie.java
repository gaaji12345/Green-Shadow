package com.gaajiFarm.GaajiFarm.service;/*  gaajiCode
    99
    03/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.VechielDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.Vehicle;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public interface VechielServie {

    public List<VechielDTO> getAllVehecl() ;




    public VechielDTO saveVehicle(VechielDTO vDTO) ;


    public void updateVehicle(VechielDTO c) ;

    public void deleteVehicle(String sid);


    public VechielDTO searchVehicle(String id) ;

    public String genarateNextVcode();
}
