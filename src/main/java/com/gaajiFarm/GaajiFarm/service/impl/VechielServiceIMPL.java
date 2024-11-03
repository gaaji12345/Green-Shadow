package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    03/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.StaffDTO;
import com.gaajiFarm.GaajiFarm.dtos.VechielDTO;
import com.gaajiFarm.GaajiFarm.service.VechielServie;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.enitity.Vehicle;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import com.gaajiFarm.GaajiFarm.util.repo.VechicleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VechielServiceIMPL implements VechielServie {

    @Autowired
    ModelMapper mapper;

    @Autowired
    VechicleRepo vechicleRepo;

    public List<VechielDTO> getAllVehecl() {
        return vechicleRepo.findAll().stream()
                .map(v -> mapper.map(v, VechielDTO.class))
                .collect(Collectors.toList());
    }




    @Override
    public VechielDTO saveVehicle(VechielDTO vDTO) {
        if(vechicleRepo.existsByVehicleCode(vDTO.getVehicleCode())){
            throw new NotFoundException("This vId "+vDTO.getVehicleCode()+" already exicts...");
        }
        vDTO.setVehicleCode(genarateNextVcode());
        return mapper.map(vechicleRepo.save(mapper.map(
                vDTO, Vehicle.class)), VechielDTO.class
        );
    }


    @Override
    public void updateVehicle(VechielDTO c) {

     Vehicle map = mapper.map(c, Vehicle.class);
        vechicleRepo.save(map);

    }

    @Override
    public void deleteVehicle(String sid) {
        vechicleRepo.deleteById(sid);
    }


    @Override
    public VechielDTO searchVehicle(String id) {

        if(!vechicleRepo.existsByVehicleCode(id)){
            throw new NotFoundException("Vid Id "+id+" Not Found!");
        }
        return mapper.map(vechicleRepo.findByVehicleCode(id), VechielDTO.class);
    }

    @Override
    public String genarateNextVcode(){
        String lastCustomerCode = vechicleRepo.findLatestVehicleCode();
        if(lastCustomerCode==null){lastCustomerCode = "VI00";}
        int numericPart = Integer.parseInt(lastCustomerCode.substring(3));
        numericPart++;
        String nextSupplierCode = "VI-" + String.format("%03d", numericPart);
        return nextSupplierCode;
    }









}
