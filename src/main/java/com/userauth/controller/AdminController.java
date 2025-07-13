package com.userauth.controller;

import com.userauth.dto.MessageResponse;
import com.userauth.dto.UserAdminView;
import com.userauth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> getDashboard() {
        return ResponseEntity.ok(new MessageResponse("Welcome to the Admin Dashboard!"));
    }

    @PostMapping("/user/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> activateUser(@PathVariable Long id) {
        adminService.activateUser(id);
        return ResponseEntity.ok(new MessageResponse("User with ID " + id + " activated successfully."));
    }

    @PostMapping("/user/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deactivateUser(@PathVariable Long id) {
        adminService.deactivateUser(id);
        return ResponseEntity.ok(new MessageResponse("User with ID " + id + " deactivated successfully."));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserAdminView>> getAllUsers() {
        List<UserAdminView> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
