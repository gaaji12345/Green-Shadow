package com.gaajiFarm.GaajiFarm.controller;/*  gaajiCode
    99
    10/09/2024
    */


import com.gaajiFarm.GaajiFarm.dtos.LoginRequest;
import com.gaajiFarm.GaajiFarm.dtos.Responce;
import com.gaajiFarm.GaajiFarm.dtos.UserDTO;
import com.gaajiFarm.GaajiFarm.service.UserService;
import com.gaajiFarm.GaajiFarm.util.ResponceUtil;
import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Responce> registerUser(@RequestBody UserDTO registrationRequest){
        System.out.println(registrationRequest);
        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Responce> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }

    @DeleteMapping
    public ResponceUtil deletCustomer(@RequestParam("email") String email){
        return new ResponceUtil(200,"Deleted", userService.deleteUser(email));

    }
    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<Responce> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<Responce> updateUser(@RequestBody UserDTO userDTO) {
        // Call the updateUser service method to update the user
        Responce response = userService.updateUser(userDTO);

        // Return the response with HTTP status 200
        return ResponseEntity.ok(response);
    }


}
