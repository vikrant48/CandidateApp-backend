package com.userauth.controller;

import com.userauth.dto.UserResponse;
import com.userauth.entity.User;
import com.userauth.entity.UserDetail;
import com.userauth.repository.UserRepository;
import com.userauth.repository.UserDetailRepository;
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

    @Autowired
    UserDetailRepository userDetailRepository;

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
            if (updatedUser.getRole() != null) user.setRole(updatedUser.getRole());

            userRepository.save(user);
            return ResponseEntity.ok(new UserResponse(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create UserDetails
    @PostMapping("/user_details")
    public ResponseEntity<UserDetail> createUserDetail(@RequestBody UserDetail userDetail) {
        UserDetail saved = userDetailRepository.save(userDetail);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user_details")
    public ResponseEntity<List<UserDetail>> getAllUserDetails() {
        return ResponseEntity.ok(userDetailRepository.findAll());
    }

    @GetMapping("/user_details/{id}")
    public ResponseEntity<UserDetail> getUserDetailById(@PathVariable Long id) {
        Optional<UserDetail> detail = userDetailRepository.findById(id);
        return detail.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user_details/{id}")
    public ResponseEntity<UserDetail> updateUserDetail(@PathVariable Long id, @RequestBody UserDetail updatedDetail) {
        Optional<UserDetail> existing = userDetailRepository.findById(id);

        if (existing.isPresent()) {
            UserDetail detail = existing.get();
            detail.setFirstName(updatedDetail.getFirstName());
            detail.setLastName(updatedDetail.getLastName());
            detail.setEmail(updatedDetail.getEmail());
            detail.setMobileNumber(updatedDetail.getMobileNumber());
            detail.setDOB(updatedDetail.getDOB());
            detail.setAddress(updatedDetail.getAddress());
            detail.setPanNumber(updatedDetail.getPanNumber());
            detail.setAadhaarNumber(updatedDetail.getAadhaarNumber());
            detail.setResumePath(updatedDetail.getResumePath());

            userDetailRepository.save(detail);
            return ResponseEntity.ok(detail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user_details/{id}")
    public ResponseEntity<Void> deleteUserDetail(@PathVariable Long id) {
        if (userDetailRepository.existsById(id)) {
            userDetailRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
