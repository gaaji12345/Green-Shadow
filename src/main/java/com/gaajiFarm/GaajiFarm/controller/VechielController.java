package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    03/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.StaffDTO;
import com.gaajiFarm.GaajiFarm.dtos.VechielDTO;
import com.gaajiFarm.GaajiFarm.service.impl.VechielServiceIMPL;
import com.gaajiFarm.GaajiFarm.util.ResponceUtil;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/car")
@CrossOrigin

public class VechielController {
    @Autowired
    VechielServiceIMPL vechielServiceIMPL;


    @GetMapping
    public ResponceUtil getAlVehiles() {

        return new ResponceUtil(200, "OK", vechielServiceIMPL.getAllVehecl());


    }


    // Save Staff
    @PostMapping
    public ResponseEntity<VechielDTO> saveVehicle(@RequestBody VechielDTO vDTO) {
        try {
            VechielDTO saveVehicle = vechielServiceIMPL.saveVehicle(vDTO);
            return new ResponseEntity<>(saveVehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    // Update Staff
    @PutMapping("/update")
    public ResponseEntity<VechielDTO> updateVehicle(@RequestBody VechielDTO vDTO) {
        try {
            vechielServiceIMPL.updateVehicle(vDTO);
            return new ResponseEntity<>(vDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping
    public  ResponceUtil deletVehicle(@RequestParam("vCode") String vCode){
        vechielServiceIMPL.deleteVehicle(vCode);
        return new ResponceUtil(200,"Deleted",null);

    }


    @GetMapping("/nextVd")
    @ResponseStatus(HttpStatus.ACCEPTED)
    String getNextStaffCode(){
        return vechielServiceIMPL.genarateNextVcode();
    }

}
