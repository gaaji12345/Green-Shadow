package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    30/10/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.FieldDTO;
import com.gaajiFarm.GaajiFarm.service.FieldService;
import com.gaajiFarm.GaajiFarm.util.enitity.Crop;
import com.gaajiFarm.GaajiFarm.util.enitity.Field;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.enums.Locations;
import com.gaajiFarm.GaajiFarm.util.repo.CropRepo;
import com.gaajiFarm.GaajiFarm.util.repo.FieldRepo;
import com.gaajiFarm.GaajiFarm.util.repo.StaffRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceIMPL implements FieldService {

    @Autowired
    FieldRepo fieldRepo;

    @Autowired
    CropRepo cropRepo;
    @Autowired
    StaffRepo staffrepo;

    @Autowired
    ModelMapper mapper;

    @Value("${field.images.directory}")
    private String imagesDirectory;



    public List<FieldDTO> getAllFields() {
        return fieldRepo.findAll().stream().map(field -> {
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(field.getFieldCode());
            fieldDTO.setFieldName(field.getFieldName());
            fieldDTO.setFieldLocation(field.getFieldLocation().name());
            fieldDTO.setSize(field.getSize());

            // Ensure crops is not null before accessing cropCode
            if (field.getCrops() != null) {
                fieldDTO.setCropCode(field.getCrops().getCropCode());
            } else {
                fieldDTO.setCropCode(null); // or set a default value if necessary
            }

            fieldDTO.setNameOfCrop(field.getNameOfCrop());
            fieldDTO.setStaffId(field.getStaff() != null ? field.getStaff().getStaffId() : null);
            fieldDTO.setFieldImage1(field.getFieldImage1());

            return fieldDTO;
        }).collect(Collectors.toList());
    }




    public FieldDTO saveField(FieldDTO fieldDTO, MultipartFile fieldImageFile) throws IOException {
        Field field = new Field();

        // Auto-generate fieldCode
        field.setFieldCode(generateFieldCode());

        // Set basic fields from DTO
        field.setFieldName(fieldDTO.getFieldName());
        field.setFieldLocation(Locations.valueOf(fieldDTO.getFieldLocation()));
        field.setSize(fieldDTO.getSize());

        // Set crop if exists
        Crop crop = cropRepo.findByCropCode(fieldDTO.getCropCode());
        if (crop != null) {
            field.setCrops(crop);
        }

//        Crop crop1 = cropRepo.findByCropCode(fieldDTO.getCropCode());
//        if (crop1 == null) {
//            throw new IllegalArgumentException("Crop not found with code: " + fieldDTO.getCropCode());
//        }



        field.setNameOfCrop(fieldDTO.getNameOfCrop());

        // Set staff if exists
        Staff staff = staffrepo.findByStaffId(fieldDTO.getStaffId());
        if (staff != null) {
            field.setStaff(staff);
        }

//        Staff staff1 = staffrepo.findByStaffId(fieldDTO.getStaffId());
//        if (staff1 == null) {
//            throw new IllegalArgumentException("StaffId not found with code: " + fieldDTO.getStaffId());
//        }


        // Save fieldImage1 in directory and set the file path in field
        if (fieldImageFile != null && !fieldImageFile.isEmpty()) {
            String filePath = saveImageToDirectory(fieldImageFile, field.getFieldCode());
            field.setFieldImage1(filePath);
        }

        field = fieldRepo.save(field);

        // Convert saved entity to DTO to return
        FieldDTO savedFieldDTO = new FieldDTO();
        savedFieldDTO.setFieldCode(field.getFieldCode());
        savedFieldDTO.setFieldName(field.getFieldName());
        savedFieldDTO.setFieldLocation(field.getFieldLocation().name());
        savedFieldDTO.setSize(field.getSize());
//        savedFieldDTO.setCropCode(field.getCrops() != null ? field.getCrops().getCropCode() : null);
        savedFieldDTO.setCropCode(field.getCrops() != null ? field.getCrops().getCropCode() : "Crop not associated");

        savedFieldDTO.setNameOfCrop(field.getNameOfCrop());
        savedFieldDTO.setStaffId(field.getStaff() != null ? field.getStaff().getStaffId() : null);
        savedFieldDTO.setFieldImage1(field.getFieldImage1()); // File path



        return savedFieldDTO;
    }

    public String generateFieldCode() {
        long count = fieldRepo.count() + 1; // Incrementing based on the current count
        return "FOO-00" + count;
    }

    private String saveImageToDirectory(MultipartFile imageFile, String fieldCode) throws IOException {
        String fileName = fieldCode + "_" + imageFile.getOriginalFilename();
        String filePath = Paths.get(imagesDirectory, fileName).toString();

        // Save file to directory
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageFile.getBytes());
        }
        return filePath;
    }





    public void deleteFiled(String employeeCode) {
        fieldRepo.deleteById(employeeCode);
    }

    public FieldDTO updateField(String fieldCode, FieldDTO fieldDTO, MultipartFile fieldImageFile) throws IOException {
        Field field = fieldRepo.findByFieldCode(fieldCode)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with code: " + fieldCode));

        // Update fields if provided in the DTO
        if (fieldDTO.getFieldName() != null) field.setFieldName(fieldDTO.getFieldName());
        if (fieldDTO.getFieldLocation() != null) field.setFieldLocation(Locations.valueOf(fieldDTO.getFieldLocation()));
        if (fieldDTO.getSize() != null) field.setSize(fieldDTO.getSize());

        // Update crop association if cropCode exists
        if (fieldDTO.getCropCode() != null) {
            Crop crop = cropRepo.findByCropCode(fieldDTO.getCropCode());
            if (crop != null) {
                field.setCrops(crop);
            } else {
                throw new IllegalArgumentException("Crop not found with code: " + fieldDTO.getCropCode());
            }
        }

        // Update staff association if staffId exists
        if (fieldDTO.getStaffId() != null) {
            Staff staff = staffrepo.findByStaffId(fieldDTO.getStaffId());
            if (staff != null) {
                field.setStaff(staff);
            } else {
                throw new IllegalArgumentException("StaffId not found with code: " + fieldDTO.getStaffId());
            }
        }

        // Update fieldImage1 if a new file is uploaded
        if (fieldImageFile != null && !fieldImageFile.isEmpty()) {
            String filePath = saveImageToDirectory(fieldImageFile, field.getFieldCode());
            field.setFieldImage1(filePath);
        }

        field = fieldRepo.save(field);

        // Convert updated entity to DTO to return
        FieldDTO updatedFieldDTO = new FieldDTO();
        updatedFieldDTO.setFieldCode(field.getFieldCode());
        updatedFieldDTO.setFieldName(field.getFieldName());
        updatedFieldDTO.setFieldLocation(field.getFieldLocation().name());
        updatedFieldDTO.setSize(field.getSize());
        updatedFieldDTO.setCropCode(field.getCrops() != null ? field.getCrops().getCropCode() : "Crop not associated");
        updatedFieldDTO.setNameOfCrop(field.getNameOfCrop());
        updatedFieldDTO.setStaffId(field.getStaff() != null ? field.getStaff().getStaffId() : null);
        updatedFieldDTO.setFieldImage1(field.getFieldImage1());

        return updatedFieldDTO;
    }
}
