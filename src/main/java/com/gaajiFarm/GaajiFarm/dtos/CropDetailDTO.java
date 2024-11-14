package com.gaajiFarm.GaajiFarm.dtos;/*  gaajiCode
    99
    14/11/2024
    */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDetailDTO {
    private String logCode;      // The logCode that maps to MonitoringLogService
    private String cropCode;     // The code for the crop
    private String staffId;// The ID of the staff
//    private String eqId;
    private int quantity;        // Quantity of the crop
    private int membersInStaff;  // Number of staff members working with the crop
//    private int equipmentQuantity;
}
