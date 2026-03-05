package com.realestate.controller;

import com.realestate.dto.LoginRequest;
import com.realestate.dto.RegisterRequest;
import com.realestate.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request){

        authService.register(request);

        return "User Registered Successfully";
    }


    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request){

        return authService.login(request);
    }
}