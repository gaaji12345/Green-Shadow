package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
public class MonitoringLogService {

    @Id
     private String logCode;

    private Timestamp logDate;

    private String logDetails;

    private UserRole role;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "field_code", nullable = false)
    private Field  field;


    @OneToMany(mappedBy = "logService", cascade = CascadeType.ALL)
    private List<CropDetails> cropDetails;


}
