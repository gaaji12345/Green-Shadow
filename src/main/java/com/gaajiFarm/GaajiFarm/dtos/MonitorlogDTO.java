package com.gaajiFarm.GaajiFarm.dtos;/*  gaajiCode
    99
    14/11/2024
    */

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitorlogDTO {
    @Null(message = "ID is auto generated")
    private String logCode;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate logDate;
    private String logDetails;
    private String role;
    private String fieldCode;
    private List<CropDetailDTO> cropDetails;
}
