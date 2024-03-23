package com.example.marketplace.controllers;

import com.example.marketplace.dto.UserRequest;
import com.example.marketplace.dto.UserResponse;
import com.example.marketplace.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/add")
    public void add(@RequestBody UserRequest request){
        userService.add(request);
    }
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }

    @GetMapping("/all")
    public List<UserResponse> all(){
        return userService.getAll();
    }
    @GetMapping("/{userId}")
    public UserResponse byId(@PathVariable Long userId){
        return userService.byId(userId);
    }
}
