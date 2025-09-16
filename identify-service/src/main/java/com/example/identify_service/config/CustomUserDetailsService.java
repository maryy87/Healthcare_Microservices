package com.example.identify_service.config;

import com.example.identify_service.entity.UserCredential;
import com.example.identify_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserCredentialRepository repository;

    @Override
    //qui verifica se la username e password sono presenti nel db prima di poter generare il token
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     Optional<UserCredential> credential= repository.findByName(username);
        return  credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
