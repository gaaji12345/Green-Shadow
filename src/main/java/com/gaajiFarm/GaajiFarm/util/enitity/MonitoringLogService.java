package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@IdClass(Monitraing_Pk.class)
public class MonitoringLogService {
//    @Id
//    private String logCode;
    @Id
    private String fieldCode;
   @Id
    private String cropCode;
     @Id
    private String staff_id;

     private String logCode;


    private LocalDate logDate;

    private String logDetails;

    @Lob
    private String observedImage;




}
