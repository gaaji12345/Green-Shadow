package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.Locations;
import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;
import java.util.List;


@Data
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @Column(name = "field_code", unique = true, nullable = false)
    private String fieldCode;

    private String fieldName;

    @Enumerated(EnumType.STRING)
    private Locations fieldLocation;

    private Double size;

    @ManyToOne
    @JoinColumn(name = "crop_code")
    private Crop crops;

    private String nameOfCrop;


    @ManyToOne
    @JoinColumn(name = "staff_id" )// This will not create a separate table
    private Staff staff;



    @Column(name = "field_image1" , columnDefinition = "LONGTEXT")
    private String fieldImage1;




}
