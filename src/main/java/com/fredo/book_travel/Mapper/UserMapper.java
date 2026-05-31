package com.fredo.book_travel.Mapper;

import com.fredo.book_travel.dto.request.UserRequest.CreateUserRequestDto;
import com.fredo.book_travel.dto.request.UserRequest.UpdateUserRequestDto;
import com.fredo.book_travel.dto.response.UserResponseDto;
import com.fredo.book_travel.entity.User;
import com.fredo.book_travel.exception.customExceptions.InvalidRoleException;
import com.fredo.book_travel.security.rolseAndPermissions.Role;

public class UserMapper {

    //---------NOTE: USERS SEND IN JSON THEN SPRING CONVERTS IT AUTOMATICALLY TO DTO
    //---------THIS METHOD CONVERTS FROM USER REQUEST DTO TO ENTITY SO IT CAN BE SENT TO THE DATABASE----------
    public static User toEntity(CreateUserRequestDto dto){
        User user = new User();
        user.setName(dto.name());
        user.setPhone(dto.phone());
        user.setUsername(dto.username());
        user.setPassword(dto.password());

        //=====MAKING SURE THAT A VALID ROLE IS ENTERED=====
        if(dto.role() == null || dto.role().trim().isEmpty()){
            throw new RuntimeException("Role is required");
        }
        String role = dto.role().trim().toUpperCase();
        Role roleValue = switch (role){
            case "ADMIN" -> Role.ADMIN;
            case "USER" -> Role.USER;
            default -> throw new InvalidRoleException("Invalid role. Only USER or  ADMIN is allowed");
        };
        user.setRole(roleValue);

        return user;
    }

    //----------UPDATING METHOD----------
    public static void UpdateToEntity(User user, UpdateUserRequestDto dto){
        user.setPhone(dto.phone());
        user.setName(dto.name());
        user.setUsername(dto.username());
        user.setPassword(dto.password());

    }

    //----------THIS METHOD CONVERTS FROM ENTITY TO USER DTO SO THE USER CAN GET A RESPONSE----------
    public static UserResponseDto toResponseDto(User user){
        return new UserResponseDto( user.getName(), user.getPhone());
    }
}
