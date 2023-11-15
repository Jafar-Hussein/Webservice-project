package com.example.AuthAndAut.repository;

import com.example.AuthAndAut.models.ApplicationUser;
import com.example.AuthAndAut.services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
