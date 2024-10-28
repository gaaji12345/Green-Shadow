package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Vehicle {
    @Id
    private String vehicleCode;

    private String licensePlateNumber;

    private String vehicleCategory;

    private String fuelType;

    private String status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff allocatedStaff;

    private String remarks;
}
