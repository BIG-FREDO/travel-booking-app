package com.fredo.book_travel.service;

import com.fredo.book_travel.Mapper.UserMapper;
import com.fredo.book_travel.dto.request.UserRequest.CreateUserRequestDto;
import com.fredo.book_travel.dto.request.UserRequest.UpdateUserRequestDto;
import com.fredo.book_travel.dto.response.UserResponseDto;
import com.fredo.book_travel.entity.User;
import com.fredo.book_travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //---------CONSTRUCTOR INJECTION---------
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder){
        this.repo = repo;
    }

    //--------THIS SECTION HANDLES ALL THE LOGIC WHEN A CALL IS BEING SENT FROM THE CONTROLLER.--------
    public UserResponseDto getUser(Integer id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponseDto(user);
    }

    public List<UserResponseDto> getUsers() {
        return repo.findAll().stream().map(UserMapper::toResponseDto).toList();
    }

    //---------CREATING OR ADDING A NEW USER---------
    public void createUser(CreateUserRequestDto dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    //--------UPDATING A USER'S DETAILS--------
    public String updateUser(UpdateUserRequestDto dto, Integer id) {
        User existing = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        //--------CONVERTING FROM DTO TO ENTITY--------
        UserMapper.UpdateToEntity(existing, dto);
        existing.setPassword(passwordEncoder.encode(existing.getPassword())); //ENCRYPTING USER PASSWORD
        User updatedUser = repo.save(existing);

        //--------NOW CONVERTING BACK TO ENTITY AND RETURNING THE VALUE--------
       UserResponseDto user = UserMapper.toResponseDto(updatedUser);
        return  user.name() + " you account has been updated";
    }

    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }

}
