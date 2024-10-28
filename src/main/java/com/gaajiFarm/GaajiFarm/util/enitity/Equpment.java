package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Equpment {
    @Id
    private String equipmentId;

    private String name;

    private String type;

    private String status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff assignedStaff;

    @ManyToOne
    @JoinColumn(name = "field_code")
    private Field assignedField;
}
