package com.fredo.book_travel.controller;

import com.fredo.book_travel.dto.request.LoginRequest.LoginRequestDto;
import com.fredo.book_travel.dto.request.UserRequest.CreateUserRequestDto;
import com.fredo.book_travel.service.securityService.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto dto){
        return authService.login(dto);
    }

    @PostMapping("/createaccount")
    public String createAccount(@RequestBody CreateUserRequestDto dto){
        return authService.createUser(dto);
    }

}
