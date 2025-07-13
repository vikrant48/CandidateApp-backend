package com.userauth.service;

import com.userauth.dto.*;
import com.userauth.repository.UserRepository;
import com.userauth.entity.User;
import com.userauth.security.UserPrincipal;
import com.userauth.security.JwtUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Authservice{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> AuthenticateUser(@Valid LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
        );

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        if (!userDetails.getRole().equals(loginRequest.getRole().name())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid role for user."));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(
                new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getRole()
                )

        );
    }

    // Register a new user
    @Transactional
    public ResponseEntity<?> registerUser(@Valid SignupRequest signupRequest){
        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already used!"));
        }

        //Create new user's account
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getName(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getRole().name()
        );
        user.setMobileNumber(signupRequest.getMobileNumber());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }




}
