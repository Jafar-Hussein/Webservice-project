package com.example.AuthAndAut.controller;

import com.example.AuthAndAut.models.LoggResponse;
import com.example.AuthAndAut.models.Post;
import com.example.AuthAndAut.models.RegisterDTO;
import com.example.AuthAndAut.models.User;
import com.example.AuthAndAut.services.AuthService;
import com.example.AuthAndAut.services.PostService;
import com.example.AuthAndAut.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterDTO body){
        return authService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoggResponse loginInfo(@RequestBody RegisterDTO body){
        return authService.loginInfo(body.getUsername(), body.getPassword());
    }


//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/addPost")
//    public ResponseEntity<?> addPost(@RequestBody Post post) {
//        if (post == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing post data");
//        }
//
//        // Get the current authenticated user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserUsername = authentication.getName();
//
//        // Load the user from the database
//        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);
//
//        // Check if the user is not null
//        if (currentUser == null) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
//        }
//
//        // Set the user to the post
//        post.setUser(currentUser);
//
//        // Save the post
//        Post savedPost = postService.savePost(post);
//
//        if (savedPost != null) {
//            return ResponseEntity.ok(savedPost);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save post");
//        }
//    }
//
//
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/getPosts")
//    public ResponseEntity<List<Post>> getPosts() {
//        List<Post> posts = postService.getAllPosts();
//        if (posts.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(posts);
//    }



}
