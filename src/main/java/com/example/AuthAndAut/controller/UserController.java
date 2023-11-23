package com.example.AuthAndAut.controller;

import com.example.AuthAndAut.models.Post;
import com.example.AuthAndAut.models.User;
import com.example.AuthAndAut.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PostService postService;
    private  User user;
    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }

//    @PreAuthorize("hasRole('USER')")
//    @PutMapping("/updatePost/{postId}")
//    public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody Post updatedPost) {
//        Post existingPost = postService.getPostById(postId).orElse(null);
//        if (existingPost == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Get the current authenticated user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserUsername = authentication.getName();
//
//        // Check if the user is the author of the post or has the necessary permissions
//        // Assuming a simple check for the author's username for demonstration purposes
//        if (!existingPost.getUsers().stream().anyMatch(user -> user.getUsername().equals(currentUserUsername))) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this post");
//        }
//
//        updatedPost.setId(postId);
//        Post savedPost = postService.savePost(updatedPost);
//
//        return ResponseEntity.ok(savedPost);
//    }

}
