package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "crops")
public class Crop {
    @Id
    private String cropCode;

    private String cropCommonName;

    private String cropScientificName;

    @Lob
    private String cropImage;

    private String category;

    private String cropSeason;

    @ManyToOne
    @JoinColumn(name = "field_code" , referencedColumnName = "field_code")
    private Field field;
}
