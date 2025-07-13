package com.userauth.service;

import com.userauth.dto.UserResponse;
import com.userauth.dto.UserDetailResponse;
import com.userauth.dto.UserAdminView;
import com.userauth.entity.User;
import com.userauth.entity.UserDetail;
import com.userauth.exception.UserNotFoundException;
import com.userauth.repository.UserDetailRepository;
import com.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    public void activateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        UserDetail detail = userDetailRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("UserDetail not found for user ID: " + userId));

        detail.setIsActive(true);
        userDetailRepository.save(detail);
    }

    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        UserDetail detail = userDetailRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("UserDetail not found for user ID: " + userId));

        detail.setIsActive(false);
        userDetailRepository.save(detail);
    }

    public List<UserAdminView> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse(user);
                    UserDetailResponse userDetailResponse = userDetailRepository.findByUser(user)
                            .map(UserDetailResponse::new)
                            .orElse(null);

                    return new UserAdminView(userResponse, userDetailResponse);
                })
                .collect(Collectors.toList());
    }
}
