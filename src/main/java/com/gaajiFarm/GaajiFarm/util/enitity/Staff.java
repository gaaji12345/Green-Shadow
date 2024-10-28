package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    27/10/2024
    */

import com.gaajiFarm.GaajiFarm.util.enums.Gender;
import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "staff")
public class Staff {
    @Id
    private String staff_id;

    private String firstName;

    private String lastName;

    private String designation;

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

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne// Reference in Field
    @JoinColumn(name = "field_code",referencedColumnName = "field_code",insertable = false,updatable = false)
    private  Field fields;


    @OneToMany(mappedBy = "assignedStaff")
    private List<Equpment> equipment;
}
