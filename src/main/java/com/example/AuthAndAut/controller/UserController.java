package com.example.AuthAndAut.controller;

import com.example.AuthAndAut.models.Post;
import com.example.AuthAndAut.models.User;
import com.example.AuthAndAut.services.PostService;
import com.example.AuthAndAut.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    private  User user;
    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        if (post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing post data");
        }

        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = authentication.getName();

        // Load the user from the database
        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);

        // Check if the user is not null
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
        }

        // Set the user to the post
        post.setUser(currentUser);

        // Save the post
        Post savedPost = postService.savePost(post);

        if (savedPost != null) {
            return ResponseEntity.ok(savedPost);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save post");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts);
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody Post updatedPost) {
        Post existingPost = postService.getPostById(postId).orElse(null);
        if (existingPost == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = authentication.getName();

        // Load the user from the database
        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);

        // Check if the user is not null
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
        }

        // Set the user to the post
        updatedPost.setUser(currentUser);


        updatedPost.setId(postId);
        Post savedPost = postService.savePost(updatedPost);

        return ResponseEntity.ok(savedPost);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        // Retrieve the post by ID from the service
        Post existingPost = postService.getPostById(postId).orElse(null);

        // Check if the post exists
        if (existingPost == null) {
            return ResponseEntity.notFound().build();
        }

        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = authentication.getName();
        // Load the user from the database
        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);

        // Check if the user is not null
        if (currentUser  == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
        }

        // Check if the authenticated user is the owner of the post
        if (!currentUser .equals(existingPost.getUser())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to delete this post");
        }

        Post post = new Post();
        post.setUser(currentUser);
        // Delete the post
        postService.deletePost(postId);

        // Return a success response
        return ResponseEntity.ok("Post deleted successfully");
    }

}
