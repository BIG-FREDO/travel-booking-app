package com.fredo.book_travel.controller;

import com.fredo.book_travel.dto.request.UserRequest.UpdateUserRequestDto;
import com.fredo.book_travel.dto.response.UserResponseDto;
import com.fredo.book_travel.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    //----This section handles all the user request: GET, POST, PUT and DELETE a user's detail from my system. from here there are calls
    //----to the service class where all the logic are being handled.
    @GetMapping("/viewProfile")
    @PreAuthorize("hasAnyRole('USER')")
    public UserResponseDto getUser(Authentication auth){
        return service.getUser(auth);
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserResponseDto> GetUsers(){
        return service.getUsers();
    }

    @PutMapping("/updateProfile")
    @PreAuthorize("hasRole('USER')")
    public String updateUser(@RequestBody UpdateUserRequestDto dto, Authentication auth){
        return service.updateUser(dto, auth);
    }

    @DeleteMapping("/deleteAccount/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public String deleteUser(@PathVariable Integer id, Authentication auth){
        return service.deleteUser(id, auth);
    }


}
