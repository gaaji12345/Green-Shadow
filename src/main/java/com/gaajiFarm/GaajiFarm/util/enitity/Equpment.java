package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.EqupmentTypes;
import com.gaajiFarm.GaajiFarm.util.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Equpment {
    @Id
    private String equipmentId;

    private String name;

    @Enumerated(EnumType.STRING)
    private EqupmentTypes type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int equntity;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff assignedStaff;

    @ManyToOne
    @JoinColumn(name = "field_code")
    private Field assignedField;


}
