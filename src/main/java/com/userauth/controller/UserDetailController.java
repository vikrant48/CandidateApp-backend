package com.userauth.controller;

import com.userauth.dto.UserDetailRequest;
import com.userauth.dto.UserDetailResponse;
import com.userauth.entity.UserDetail;
import com.userauth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user_details")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> createUserDetail(@PathVariable Long userId,
                                                               @RequestBody UserDetailRequest request) {
        UserDetail detail = mapToEntity(request);
        UserDetail saved = userDetailService.createUserDetail(userId, detail);
        return ResponseEntity.ok(new UserDetailResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<UserDetailResponse>> getAllUserDetails() {
        List<UserDetailResponse> details = userDetailService.getAllUserDetails()
                .stream()
                .map(UserDetailResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(details);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailResponse> getUserDetailById(@PathVariable Long id) {
        UserDetail detail = userDetailService.getUserDetailById(id);
        return ResponseEntity.ok(new UserDetailResponse(detail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailResponse> updateUserDetail(@PathVariable Long id,
                                                               @RequestBody UserDetailRequest request) {
        UserDetail updated = userDetailService.updateUserDetail(id, mapToEntity(request));
        return ResponseEntity.ok(new UserDetailResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserDetail(@PathVariable Long id) {
        userDetailService.deleteUserDetail(id);
        return ResponseEntity.ok("UserDetail deleted");
    }

    // Helper to convert DTO to Entity
    private UserDetail mapToEntity(UserDetailRequest req) {
        UserDetail detail = new UserDetail();
        detail.setFirstName(req.getFirstName());
        detail.setLastName(req.getLastName());
        detail.setAlternateEmail(req.getAlternateEmail());
        detail.setAlternateMobileNumber(req.getAlternateMobileNumber());
        detail.setDOB(req.getDOB());
        detail.setAge(req.getAge());
        detail.setGender(req.getGender());
        detail.setAddress(req.getAddress());
        detail.setIsActive(req.getIsActive());
        detail.setPanNumber(req.getPanNumber());
        detail.setAadhaarNumber(req.getAadhaarNumber());
        detail.setBloodGroup(req.getBloodGroup());
        detail.setEmergencyContactName(req.getEmergencyContactName());
        detail.setEmergencyContactNumber(req.getEmergencyContactNumber());
        detail.setEmergencyContactRelation(req.getEmergencyContactRelation());
        return detail;
    }
}
