package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    01/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.StaffDTO;
import com.gaajiFarm.GaajiFarm.service.impl.StaffServiceIMPL;
import com.gaajiFarm.GaajiFarm.util.ResponceUtil;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/staff")
@CrossOrigin
public class StaffController {


    @Autowired
    StaffServiceIMPL staffServiceIMPL;
    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponceUtil getAlFields() {

        return new ResponceUtil(200, "OK", staffServiceIMPL.getAllCrops());


    }


        // Save Staff
        @PostMapping
        public ResponseEntity<StaffDTO> saveStaff(@RequestBody StaffDTO staffDTO) {
            try {
                StaffDTO savedStaff = staffServiceIMPL.saveStaff(staffDTO);
                return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }

        // Update Staff
        @PutMapping("/update")
        public ResponseEntity<StaffDTO> updateStaff(@RequestBody StaffDTO staffDTO) {
            try {
                staffServiceIMPL.updateStaff(staffDTO);
                return new ResponseEntity<>(staffDTO, HttpStatus.OK);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }

        // Delete Staff
//        @DeleteMapping("/delete/{id}")
//        public ResponseEntity<String> deleteStaff(@PathVariable String id) {
//            try {
//                staffServiceIMPL.deleteStaff(id);
//                return new ResponseEntity<>("Staff ID " + id + " deleted successfully.", HttpStatus.OK);
//            } catch (NotFoundException e) {
//                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//            }
//        }

    @DeleteMapping
    public  ResponceUtil deletCustomer(@RequestParam("sCode") String sCode){
        staffServiceIMPL.deleteStaff(sCode);
        return new ResponceUtil(200,"Deleted",null);

    }


}
