package com.example.AuthAndAut.services;

import com.example.AuthAndAut.models.Post;
import com.example.AuthAndAut.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(int postId) {
        return postRepository.findById(postId);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }
}
