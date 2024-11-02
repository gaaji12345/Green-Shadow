package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

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

    private String cropCommonName;

    private String cropScientificName;

    @Lob
    private String cropImage;

    private String category;

    private int qty;

    private String cropSeason;

    private String fieldCodes;

    private String filedNames;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "crops")
    private List<Field> filed = new ArrayList<>();





}
