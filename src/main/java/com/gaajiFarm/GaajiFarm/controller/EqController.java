package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    05/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.EqDTO;
import com.gaajiFarm.GaajiFarm.dtos.VechielDTO;
import com.gaajiFarm.GaajiFarm.service.impl.EqServiceIMPL;
import com.gaajiFarm.GaajiFarm.util.ResponceUtil;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/eq")
@CrossOrigin
public class EqController {

    @Autowired
    EqServiceIMPL eqServiceIMPL;


    @GetMapping
    public ResponceUtil getAlEq() {

        return new ResponceUtil(200, "OK", eqServiceIMPL.getAllEq());
    }

    @PostMapping
    public ResponseEntity<EqDTO> saveEq(@RequestBody EqDTO eDTO) {
        try {
            EqDTO savee = eqServiceIMPL.saveEq(eDTO);
            return new ResponseEntity<>(savee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<EqDTO> updateEq(@RequestBody EqDTO eDTO) {
        try {
            eqServiceIMPL.updateEq(eDTO);
            return new ResponseEntity<>(eDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping
    public  ResponceUtil deleteEq(@RequestParam("eCode") String eCode){
        eqServiceIMPL.deleteEq(eCode);
        return new ResponceUtil(200,"Deleted",null);

    }


    @GetMapping("/nextEId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    String getNextStaffCode(){
        return eqServiceIMPL.genarateNextEcode();
    }




}
