package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.Fuel;
import com.gaajiFarm.GaajiFarm.util.enums.Status;
import com.gaajiFarm.GaajiFarm.util.enums.VTypes;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicle {
    @Id
    private String vehicleCode;

    private String licensePlateNumber;

    @Enumerated(EnumType.STRING)
    private VTypes vehicleCategory;

    @Enumerated(EnumType.STRING)
    private Fuel fuelType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff allocatedStaff;

    private String remarks;




}
