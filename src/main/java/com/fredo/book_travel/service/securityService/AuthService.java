package com.fredo.book_travel.service.securityService;

import com.fredo.book_travel.dto.request.LoginRequest.LoginRequestDto;
import com.fredo.book_travel.entity.User;
import com.fredo.book_travel.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        if(passwordEncoder.matches(dto.password(), user.getPassword())){
            return "Hello " + user.getName() + "! Welcome Back.\n";
        }
        return "Incorrect Password \n";
    }
}
