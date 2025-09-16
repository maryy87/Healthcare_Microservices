package com.example.identify_service.service;

import com.example.identify_service.entity.UserCredential;
import com.example.identify_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential userCredential) {
/// Quella riga serve a proteggere la password prima di salvarla nel
        //database, trasformandola in un hash sicuro.
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));

        userCredentialRepository.save(userCredential);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
