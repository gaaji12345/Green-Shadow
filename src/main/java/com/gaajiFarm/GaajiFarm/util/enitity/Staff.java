package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.Designation;
import com.gaajiFarm.GaajiFarm.util.enums.Gender;
import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_id", unique = true, nullable = false)
    private String staffId;

    private String firstName;

    private String lastName;

  @Enumerated(EnumType.STRING)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate joinedDate;

    private LocalDate dob;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;

    private String contactNo;

    private String email;

    private int members;

    private String fieldCode;
    private String vCode;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "staff")
    private List<Field> filed = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "allocatedStaff")
    private List<Vehicle> vehicles = new ArrayList<>();










}
