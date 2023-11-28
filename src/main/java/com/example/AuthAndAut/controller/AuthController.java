package com.example.AuthAndAut.controller;

import com.example.AuthAndAut.models.LoggResponse;
import com.example.AuthAndAut.models.RegisterDTO;
import com.example.AuthAndAut.models.User;
import com.example.AuthAndAut.services.AuthService;
import com.example.AuthAndAut.services.PostService;
import com.example.AuthAndAut.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    // Injicera AuthService för autentisering
    @Autowired
    private AuthService authService;

    // Injicera PostService för att hantera inlägg
    @Autowired
    private PostService postService;

    // Injicera UserService för att hantera användarinformation
    @Autowired
    private UserService userService;

    // Registrera en ny användare
    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterDTO body) {
        return authService.registerUser(body);
    }

    // Logga in och returnera loggningsinformation
    @PostMapping("/login")
    public LoggResponse loginInfo(@RequestBody RegisterDTO body) {
        return authService.loginInfo(body.getUsername(), body.getPassword());
    }

}
