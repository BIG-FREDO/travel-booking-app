package com.fredo.book_travel.Mapper;

import com.fredo.book_travel.dto.request.UserRequest.CreateUserRequestDto;
import com.fredo.book_travel.dto.request.UserRequest.UpdateUserRequestDto;
import com.fredo.book_travel.dto.response.UserResponseDto;
import com.fredo.book_travel.entity.User;

public class UserMapper {

    //---------NOTE: USERS SEND IN JSON THEN SPRING CONVERTS IT AUTOMATICALLY TO DTO
    //---------THIS METHOD CONVERTS FROM USER REQUEST DTO TO ENTITY SO IT CAN BE SENT TO THE DATABASE----------
    public static User toEntity(CreateUserRequestDto createUserRequestDto){
        User user = new User();
        user.setName(createUserRequestDto.name());
        user.setPhone(createUserRequestDto.phone());
        user.setUsername(createUserRequestDto.username());
        user.setPassword(createUserRequestDto.password());
        return user;
    }

    //----------UPDATING METHOD-----------------
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
