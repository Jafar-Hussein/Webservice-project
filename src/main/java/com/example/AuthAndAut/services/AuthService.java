package com.example.AuthAndAut.services;

import com.example.AuthAndAut.models.LoggResponse;
import com.example.AuthAndAut.models.RegisterDTO;
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
    // Registrera en ny användare
    public User registerUser(RegisterDTO registerDTO) {
        // Kryptera lösenordet
        String encodedPassword = encoder.encode(registerDTO.getPassword());

        // Hämta användarrollen (USER) från databasen eller kasta ett undantag om den inte finns
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RuntimeException("Användarroll hittades inte"));

        // Skapa en uppsättning roller för användaren
        Set<Role> roller = new HashSet<>();
        roller.add(userRole);

        // Spara användaren i databasen
        return userRepository.save(new User(0, registerDTO.getUsername(), encodedPassword, roller));
    }

    // Logga in och returnera loggningsinformation
    public LoggResponse loginInfo(String användarnamn, String lösenord) {
        try {
            // Autentisera användaren
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(användarnamn, lösenord)
            );

            // Generera en JWT-token
            String token = tokenService.generateJwt(auth);

            // Returnera loggningsresponsen med användarinformation och token
            return new LoggResponse(userRepository.findByUsername(användarnamn).get(), token);

        } catch (AuthenticationException e) {
            // Returnera en tom loggningsrespons om autentiseringen misslyckas
            return new LoggResponse(null, "");
        }
    }
}
