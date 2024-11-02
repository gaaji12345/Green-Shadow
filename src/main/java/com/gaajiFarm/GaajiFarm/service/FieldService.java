package com.gaajiFarm.GaajiFarm.service;/*  gaajiCode
    99
    30/10/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.FieldDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.Crop;
import com.gaajiFarm.GaajiFarm.util.enitity.Field;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.enums.Locations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public interface FieldService {
    public List<FieldDTO> getAllFields() ;

    public FieldDTO saveField(FieldDTO fieldDTO, MultipartFile fieldImageFile) throws IOException ;

    public String generateFieldCode() ;

    public void deleteFiled(String employeeCode);

    public FieldDTO updateField(String fieldCode, FieldDTO fieldDTO, MultipartFile fieldImageFile) throws IOException ;
}
