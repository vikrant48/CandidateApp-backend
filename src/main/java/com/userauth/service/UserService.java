package com.userauth.service;

import org.springframework.stereotype.Service;
import com.userauth.repository.UserRepository;
import com.userauth.dto.UserResponse;
import com.userauth.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.userauth.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.userauth.security.UserPrincipal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return new UserResponse(user);
    }


    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found" + id));
        return new UserResponse(user);
    }

    public UserResponse updateUserProfile(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found" + userId));

        // Update only non-null fields
        if (updatedUser.getName() != null) user.setName(updatedUser.getName());
        if (updatedUser.getMobileNumber() != null) user.setMobileNumber(updatedUser.getMobileNumber());
        if (updatedUser.getRole() != null) user.setRole(updatedUser.getRole());

        userRepository.save(user);
        return new UserResponse(user);
    }

}
