package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

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

//    @Column(columnDefinition = "geometry(Point, 4326)") // Assuming PostGIS for geographic data
    private String fieldLocation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "field")
    private List<Crop> crops;


    @OneToMany
    @JoinColumn(name = "staff_id") // This will not create a separate table
    private List<Staff> staff;

    @Lob
    private String fieldImage1;

    @Lob
    @Transient
    private String fieldImage2;
}
