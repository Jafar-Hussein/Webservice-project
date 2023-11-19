package com.example.AuthAndAut.controller;

import com.example.AuthAndAut.models.LoggResponse;
import com.example.AuthAndAut.models.RegisterDTO;
import com.example.AuthAndAut.models.User;
import com.example.AuthAndAut.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterDTO body){
        return authService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoggResponse loginInfo(@RequestBody RegisterDTO body){
        return authService.loginInfo(body.getUsername(), body.getPassword());
    }
}
