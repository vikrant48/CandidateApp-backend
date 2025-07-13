package com.userauth.service;

import com.userauth.entity.User;
import com.userauth.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.userauth.entity.UserDetail;
import com.userauth.repository.UserDetailRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserRepository userRepository;

    public UserDetail createUserDetail(Long userId, UserDetail userDetail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userDetail.setUser(user);
        return userDetailRepository.save(userDetail);
    }

    public List<UserDetail> getAllUserDetails() {
        return userDetailRepository.findAll();
    }

    public UserDetail getUserDetailById(Long id) {
        return userDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserDetail not found"));
    }

    public UserDetail updateUserDetail(Long id, UserDetail updatedDetail) {
        UserDetail detail = userDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserDetail not found"));

        detail.setFirstName(updatedDetail.getFirstName());
        detail.setLastName(updatedDetail.getLastName());
        detail.setDOB(updatedDetail.getDOB());
        detail.setAge(updatedDetail.getAge());
        detail.setAddress(updatedDetail.getAddress());
        detail.setPanNumber(updatedDetail.getPanNumber());
        detail.setAadhaarNumber(updatedDetail.getAadhaarNumber());
        detail.setIsActive(updatedDetail.getIsActive());
        detail.setAlternateEmail(updatedDetail.getAlternateEmail());
        detail.setAlternateMobileNumber(updatedDetail.getAlternateMobileNumber());
        detail.setCountry(updatedDetail.getCountry());
        detail.setEmergencyContactName(updatedDetail.getEmergencyContactName());
        detail.setEmergencyContactNumber(updatedDetail.getEmergencyContactNumber());
        detail.setEmergencyContactRelation(updatedDetail.getEmergencyContactRelation());

        return userDetailRepository.save(detail);
    }

    public void deleteUserDetail(Long id) {
        if (!userDetailRepository.existsById(id)) {
            throw new RuntimeException("UserDetail not found");
        }
        userDetailRepository.deleteById(id);
    }
}
