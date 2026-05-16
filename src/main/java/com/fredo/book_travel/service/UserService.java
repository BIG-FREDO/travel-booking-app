package com.fredo.book_travel.service;

import com.fredo.book_travel.Mapper.UserMapper;
import com.fredo.book_travel.dto.request.UserRequest.UpdateUserRequestDto;
import com.fredo.book_travel.dto.response.UserResponseDto;
import com.fredo.book_travel.entity.User;
import com.fredo.book_travel.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    //---------CONSTRUCTOR INJECTION---------
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    //--------THIS SECTION HANDLES ALL THE LOGIC WHEN A CALL IS BEING SENT FROM THE CONTROLLER.--------
    public UserResponseDto getUser(Authentication auth) {

        String username = auth.getName();
        User user = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponseDto(user);
    }

    public List<UserResponseDto> getUsers() {
        return repo.findAll().stream().map(UserMapper::toResponseDto).toList();
    }

    //--------UPDATING A USER'S DETAILS--------
    public String updateUser(UpdateUserRequestDto dto, Authentication auth) {

        String username = auth.getName();
        User existing = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        //--------CONVERTING FROM DTO TO ENTITY--------
        UserMapper.UpdateToEntity(existing, dto);
        existing.setPassword(passwordEncoder.encode(existing.getPassword())); //ENCRYPTING USER PASSWORD----
        User updatedUser = repo.save(existing);

        //--------NOW CONVERTING BACK TO ENTITY AND RETURNING THE VALUE--------
       UserResponseDto user = UserMapper.toResponseDto(updatedUser);
        return  user.name() + " you account has been updated";
    }

    public String deleteUser(Integer id, Authentication auth) {
        //----HERE WE SEARCH FOR THE CURRENT LOGGED-IN USER FROM THE JWT TOKEN THAT WILL BE PROVIDED IN THE HEADER----
        String username = auth.getName();

        repo.findByUsername(username).orElseThrow(() -> new RuntimeException("Delete failed"));
        repo.deleteById(id);
        return "Account deleted: "+ username;
    }

}
