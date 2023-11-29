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
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    // Injicera PostService för att hantera inlägg
    @Autowired
    private PostService postService;
    // Injicera UserService för att hantera användarinformation
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }

    // Lägg till ett inlägg för den autentiserade användaren
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        if (post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing post data");
        }

        // Hämta den aktuella autentiserade användaren
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = authentication.getName();

        // Ladda användaren från databasen
        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);

        // Kontrollera om användaren inte är null
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
        }

        // Sätt användaren till inlägget
        post.setUser(currentUser);

        // Spara inlägget
        Post savedPost = postService.savePost(post);

        if (savedPost != null) {
            return ResponseEntity.ok(savedPost);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save post");
        }
    }

    // Hämta alla inlägg
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getPostById/{id}") // hämtar bara ett inlägg
    public ResponseEntity<?> getPostById(@PathVariable int id){
        Optional<Post> post = postService.getPostById(id);
        return ResponseEntity.ok(post.orElse(null));

    }
    // Uppdatera ett inlägg för administratörsrollen
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody Post updatedPost) {
        Post existingPost = postService.getPostById(postId).orElse(null);

        // Kontrollera om inlägget finns
        if (existingPost == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = authentication.getName();

        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
        }
        updatedPost.setUser(currentUser);


        updatedPost.setId(postId);
        postService.savePost(updatedPost);

        return ResponseEntity.ok("Post updated successfully");
    }
    // Radera ett inlägg för administratörsrollen
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        // Hämta inlägget efter ID från tjänsten
        Post existingPost = postService.getPostById(postId).orElse(null);

        // Kontrollera om inlägget finns
        if (existingPost == null) {
            return ResponseEntity.notFound().build();
        }

        // Hämta den autentiserade användaren
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = authentication.getName();

        // Ladda användaren från databasen
        User currentUser = (User) userService.loadUserByUsername(currentUserUsername);

        // Kontrollera om användaren inte är null
        if (currentUser  == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get current user");
        }

        Post post = new Post();
        post.setUser(currentUser);

        // Radera inlägget
        postService.deletePost(postId);

        // Returnera ett lyckat svar
        return ResponseEntity.ok("Post deleted successfully");
    }

}
