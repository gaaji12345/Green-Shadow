package com.gaajiFarm.GaajiFarm.util.enitity;/*  gaajiCode
    99
    28/10/2024
    */

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipient;
    private String subject;
    private String body;
}
