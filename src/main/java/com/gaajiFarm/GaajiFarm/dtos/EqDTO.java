package com.gaajiFarm.GaajiFarm.dtos;/*  gaajiCode
    99
    05/11/2024
    */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EqDTO {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private int equantity;
    private String assignedStaffId;
    private String assignedFieldCode;
}
