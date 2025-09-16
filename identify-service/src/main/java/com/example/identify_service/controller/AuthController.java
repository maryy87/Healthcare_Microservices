package com.example.identify_service.controller;

import com.example.identify_service.dto.AuthRequest;
import com.example.identify_service.entity.UserCredential;
import com.example.identify_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential userCredential, HttpServletRequest request) {
        // Stampa il percorso della richiesta ricevuta
        System.out.println("Request URI: " + request.getRequestURI());

        // Stampa URL completo
        System.out.println("Request URL: " + request.getRequestURL());
        return authService.saveUser(userCredential);
    }


    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        }
       else {
           throw new RuntimeException("invalid access!");
        }
    }

    @GetMapping("/validate")
    public String validToken(@RequestParam("token") String token) {
        authService.validateToken(token);

        return "token is valid";
    }

}
