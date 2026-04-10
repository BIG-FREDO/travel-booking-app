package com.fredo.book_travel.controller;

import com.fredo.book_travel.dto.request.UserRequest.CreateUserRequestDto;
import com.fredo.book_travel.dto.request.UserRequest.UpdateUserRequestDto;
import com.fredo.book_travel.dto.response.UserResponseDto;
import com.fredo.book_travel.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    //This section handles all the user request: GET, POST, PUT and DELETE a user's detail from my system. from here there are calls
    //to the service class where all the logic are being handled.
    @GetMapping("/getuser/{id}")
    public UserResponseDto getUser(@PathVariable Integer id){
        return service.getUser(id);
    }

    @GetMapping("/getusers")
    public List<UserResponseDto> GetUsers(){
        return service.getUsers();
    }

    @PostMapping("/createaccount")
    public void createUser(@RequestBody CreateUserRequestDto dto){
        service.createUser(dto);
    }

    @PutMapping("/updateuser/{id}")
    public String updateUser(@RequestBody UpdateUserRequestDto dto, @PathVariable Integer id){
        return service.updateUser(dto, id);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
    }
}
