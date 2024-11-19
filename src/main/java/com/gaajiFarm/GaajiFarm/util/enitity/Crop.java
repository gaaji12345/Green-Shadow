package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.CropCategory;
import com.gaajiFarm.GaajiFarm.util.enums.CropComnName;
import com.gaajiFarm.GaajiFarm.util.enums.CropScineceName;
import com.gaajiFarm.GaajiFarm.util.enums.CropSesasons;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "crops")
public class Crop {
    @Id
    @Column(name = "crop_code")
    private String cropCode;

    @Enumerated(EnumType.STRING)
    private CropComnName cropCommonName;

    @Enumerated(EnumType.STRING)
    private CropScineceName cropScientificName;

    @Column(name = "crop_image" , columnDefinition = "LONGTEXT")
    private String cropImage;

    @Enumerated(EnumType.STRING)
    private CropCategory category;

    private int qty;

    @Enumerated(EnumType.STRING)
    private CropSesasons cropSeason;

    private String fieldCodes;

    private String filedNames;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "crops")
    private List<Field> filed = new ArrayList<>();





}
