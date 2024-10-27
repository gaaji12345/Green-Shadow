package com.gaajiFarm.GaajiFarm.util.mapper;/*  gaajiCode
    99
    26/10/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.UserDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.User;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    public UserDTO mapUserToDtoBasic(User user){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        userDto.setName(user.getName());
        return userDto;

    }
}
