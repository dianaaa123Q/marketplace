package com.example.marketplace.controllers;

import com.example.marketplace.dto.LoginRequest;
import com.example.marketplace.dto.LoginResponse;
import com.example.marketplace.dto.UserRequest;
import com.example.marketplace.dto.UserResponse;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/register")
    public void register(@RequestBody UserRequest request){
        userService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String confirmCode){
        return userService.confirm(confirmCode);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return userService.login(request);
    }


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
