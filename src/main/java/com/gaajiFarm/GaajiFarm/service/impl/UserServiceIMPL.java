package com.gaajiFarm.GaajiFarm.service.impl;/*  gaajiCode
    99
    26/10/2024
    */

import com.gaajiFarm.GaajiFarm.config.JwtUtils;
import com.gaajiFarm.GaajiFarm.dtos.LoginRequest;
import com.gaajiFarm.GaajiFarm.dtos.Responce;
import com.gaajiFarm.GaajiFarm.dtos.UserDTO;
import com.gaajiFarm.GaajiFarm.service.UserService;
import com.gaajiFarm.GaajiFarm.util.enitity.User;
import com.gaajiFarm.GaajiFarm.util.enums.UserRole;
import com.gaajiFarm.GaajiFarm.util.exeptions.InvalidCredentialsException;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import com.gaajiFarm.GaajiFarm.util.mapper.EntityDtoMapper;
import com.gaajiFarm.GaajiFarm.util.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;



    @Override
    public Responce registerUser(UserDTO registrationRequest) {
        UserRole role = UserRole.ADMINISTRATIVE; // Default role

        // Determine the user role based on the input
        if (registrationRequest.getRole() != null) {
            if (registrationRequest.getRole().equalsIgnoreCase("manager")) {
                role = UserRole.MANAGER;
            } else if (registrationRequest.getRole().equalsIgnoreCase("scientist")) {
                role = UserRole.SCIENTIST;
            }
            // Optionally, you can handle invalid roles here
        }

        // Create a new User object
        User user = User.builder()
                .id(registrationRequest.getId())
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .build();

        // Save the user to the repository
        User savedUser = userRepo.save(user);
        System.out.println(savedUser);

        // Map the saved user to a DTO
        UserDTO userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);

        // Return a successful response
        return Responce.builder()
                .status(200)
                .message("User Successfully Added")
                .user(userDto)
                .build();
    }


@Override
public Responce loginUser(LoginRequest loginRequest) {
    User user = userRepo.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new NotFoundException("Email not found"));

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        throw new InvalidCredentialsException("Password does not match");
    }

    String token = jwtUtils.generateToken(user);

    // Customizing response message based on user role
    String roleMessage;
    switch (user.getRole()) {
        case MANAGER:
            roleMessage = "Manager logs in";
            break;
        case ADMINISTRATIVE:
            roleMessage = "Administrator logs in";
            break;
        case SCIENTIST:
            roleMessage = "Scientist logs in";
            break;
        default:
            roleMessage = "User logs in";
            break;
    }

    return Responce.builder()
            .status(200)
            .message(roleMessage)
            .token(token)
            .expirationTime("6 Months")
            .role(user.getRole().name())
            .build();
}


    @Override
    public Responce getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDtos = users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        return Responce.builder()
                .status(200)
                .userList(userDtos)
                .build();
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  email = authentication.getName();
        log.info("User Email is: " + email);
        return userRepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found"));
    }



    @Override
    public Responce deleteUser(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + email));

        userRepo.delete(user);

        return Responce.builder()
                .status(200)
                .message("User successfully deleted")
                .build();
    }

    public List<UserDTO> getAllUsersll(UserRole currentUserRole) throws AccessDeniedException {
        // Check if the current user has permission to get all users
        if (currentUserRole != UserRole.MANAGER) {
            throw new AccessDeniedException("Access denied: Only MANAGER can view all users.");
        }

        // Fetch all users from the repository
        List<User> users = userRepo.findAll();

        // Map users to UserDTOs
        return users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .collect(Collectors.toList());
    }

    @Override
    public Responce updateUser(UserDTO updateUserRequest) {
        log.info("Updating user with email: {}", updateUserRequest.getEmail());

        // Fetch the existing user from the repository
        User existingUser = userRepo.findByEmail(updateUserRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + updateUserRequest.getEmail()));

        // Update user details
        existingUser.setName(updateUserRequest.getName());
        existingUser.setPhoneNumber(updateUserRequest.getPhoneNumber());
        existingUser.setRole(UserRole.valueOf((updateUserRequest.getRole())));

        // Optionally update password if provided
        if (updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }

        // Save updated user to the repository
        User savedUser = userRepo.save(existingUser);
        log.info("User with email {} updated successfully.", updateUserRequest.getEmail());

        // Map the updated user to a DTO
        UserDTO userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);

        // Return successful response
        return Responce.builder()
                .status(200)
                .message("User successfully updated")
                .user(userDto)
                .build();
    }


}
