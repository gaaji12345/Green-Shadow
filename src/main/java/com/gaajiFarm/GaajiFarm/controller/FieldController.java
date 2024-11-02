package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    30/10/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.FieldDTO;
import com.gaajiFarm.GaajiFarm.service.impl.FieldServiceIMPL;
import com.gaajiFarm.GaajiFarm.util.ResponceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auth/field")
@CrossOrigin
public class FieldController {

    @Autowired
    FieldServiceIMPL fieldServiceIMPL;


    @PostMapping("/save")
    public ResponseEntity<FieldDTO> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("size") Double size,
            @RequestParam(value = "cropCode") String cropCode,
            @RequestParam(value = "nameOfCrop") String nameOfCrop,
            @RequestParam(value = "staffId") String staffId,
            @RequestParam(value = "fieldImageFile", required = false) MultipartFile fieldImageFile
    ) {
        try {
            // Create and populate FieldDTO
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setSize(size);
            fieldDTO.setCropCode(cropCode);
            fieldDTO.setNameOfCrop(nameOfCrop);
            fieldDTO.setStaffId(staffId);

            // Call service to save the field
            FieldDTO savedField = fieldServiceIMPL.saveField(fieldDTO, fieldImageFile);
            return new ResponseEntity<>(savedField, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/generateFieldCode")
    public ResponseEntity<String> generateFieldCode() {
        String newFieldCode = fieldServiceIMPL.generateFieldCode();
        return ResponseEntity.ok(newFieldCode);
    }

    @DeleteMapping
//    @PreAuthorize("hasAuthority('MANAGER')")
    public  ResponceUtil deletCustomer(@RequestParam("fCode") String fCode){
        fieldServiceIMPL.deleteFiled(fCode);
        return new ResponceUtil(200,"Deleted",null);

    }





    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponceUtil getAlFields() {

        return new ResponceUtil(200, "OK", fieldServiceIMPL.getAllFields());
    }





    @PutMapping("/update")
    public ResponseEntity<FieldDTO> updateField(
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("size") Double size,
            @RequestParam(value = "cropCode", required = false) String cropCode,
            @RequestParam(value = "nameOfCrop", required = false) String nameOfCrop,
            @RequestParam(value = "staffId", required = false) String staffId,
            @RequestParam(value = "fieldImageFile", required = false) MultipartFile fieldImageFile
    ) throws IOException {

        // Create and populate FieldDTO with request parameters
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldName(fieldName);
        fieldDTO.setFieldLocation(fieldLocation);
        fieldDTO.setSize(size);
        fieldDTO.setCropCode(cropCode);
        fieldDTO.setNameOfCrop(nameOfCrop);
        fieldDTO.setStaffId(staffId);

        // Call the update service
        FieldDTO updatedField = fieldServiceIMPL.updateField(fieldCode, fieldDTO, fieldImageFile);

        return ResponseEntity.ok(updatedField);
    }

}
