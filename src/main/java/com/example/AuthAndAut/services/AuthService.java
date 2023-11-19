package com.example.AuthAndAut.services;

import com.example.AuthAndAut.models.LoggResponse;
import com.example.AuthAndAut.models.User;
import com.example.AuthAndAut.models.Role;
import com.example.AuthAndAut.repository.RoleRepository;
import com.example.AuthAndAut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private  TokenService tokenService;
    public User registerUser(String username, String password){
        String encodedPassword = encoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new User(0, username, encodedPassword, authorities));
    }

    public LoggResponse loginInfo(String username, String password){

        try{
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoggResponse(userRepository.findByUsername(username).get(), token);

        }catch (AuthenticationException e){
            return new LoggResponse(null,"");
        }
    }
}
