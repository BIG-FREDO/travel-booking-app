package com.fredo.book_travel.service.securityService;

import com.fredo.book_travel.Mapper.UserMapper;
import com.fredo.book_travel.dto.request.LoginRequest.LoginRequestDto;
import com.fredo.book_travel.dto.request.UserRequest.CreateUserRequestDto;
import com.fredo.book_travel.entity.User;
import com.fredo.book_travel.exception.customExceptions.InvalidCredentialsException;
import com.fredo.book_travel.repository.UserRepository;
import com.fredo.book_travel.security.jwtFilters.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    public String login(LoginRequestDto dto) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
            );
            return jwtUtil.generateToken(dto.username());
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid Username or password");
        }
    }

    public String createUser(CreateUserRequestDto dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getName() + " Your account has been created successfully!.";
    }
}
