package com.userauth.controller;

import com.userauth.dto.UserResponse;
import com.userauth.entity.User;
import com.userauth.repository.UserRepository;
import com.userauth.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Optional<User> userOptional = userRepository.findById(userPrincipal.getId());
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(new UserResponse(userOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(new UserResponse(userOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody User updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Optional<User> userOptional = userRepository.findById(userPrincipal.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update only non-null fields
            if (updatedUser.getName() != null) user.setName(updatedUser.getName());
            if (updatedUser.getAge() != null) user.setAge(updatedUser.getAge());
            if (updatedUser.getMobileNumber() != null) user.setMobileNumber(updatedUser.getMobileNumber());
            if (updatedUser.getCountry() != null) user.setCountry(updatedUser.getCountry());
            if (updatedUser.getGender() != null) user.setGender(updatedUser.getGender());

            userRepository.save(user);
            return ResponseEntity.ok(new UserResponse(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
