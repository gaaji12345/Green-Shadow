package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class MonitoringLogService {

    @Id
     private String logCode;

    private LocalDate logDate;

    private String logDetails;


    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "field_code", nullable = false)
    private Field  field;


    // Initialize the cropDetails list to avoid NullPointerException
    @OneToMany(mappedBy = "logService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CropDetails> cropDetails = new ArrayList<>(); // Initialize with an empty list


}
