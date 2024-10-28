package com.gaajiFarm.GaajiFarm.service;/*  gaajiCode
    99
    26/10/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.LoginRequest;
import com.gaajiFarm.GaajiFarm.dtos.Responce;
import com.gaajiFarm.GaajiFarm.dtos.UserDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.User;
import com.gaajiFarm.GaajiFarm.util.enums.UserRole;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {
    Responce registerUser(UserDTO registrationRequest);

    // Responce registerUser(UserDTO registrationRequest, UserRole currentUserRole);
    Responce loginUser(LoginRequest loginRequest);

    Responce getAllUsers();

    User getLoginUser();
//    public Responce deleteUser(Long id);

    //    public void deleteUser(String email);
    public Responce deleteUser(String email);

    public List<UserDTO> getAllUsersll(UserRole currentUserRole) throws AccessDeniedException;

    public Responce updateUser(UserDTO updateUserRequest);

}
