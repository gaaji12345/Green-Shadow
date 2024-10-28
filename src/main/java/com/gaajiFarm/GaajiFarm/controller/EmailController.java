package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    28/10/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.EmailRequest;
import com.gaajiFarm.GaajiFarm.service.impl.EmailServiceIMPL;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email/")
public class EmailController {

    @Autowired
    private EmailServiceIMPL emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequest emailRequest){
        return  ResponseEntity.ok(emailService.sendEmail(emailRequest));
    }
}
