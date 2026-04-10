package com.fredo.book_travel.Mapper;

import com.fredo.book_travel.dto.request.LoginRequest.LoginRequestDto;
import com.fredo.book_travel.dto.response.LoginResponseDto;
import com.fredo.book_travel.entity.User;

public class LoginMapper {

    public static User toEntity(LoginRequestDto dto){
       User user = new User();
       user.setUsername(dto.username());
       user.setPassword(dto.password());
       return user;
    }

    public static LoginResponseDto toDto(User user){
        return new LoginResponseDto(user.getUsername());
    }
}
