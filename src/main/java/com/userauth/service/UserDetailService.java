package com.userauth.service;

import com.userauth.entity.User;
import com.userauth.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.userauth.entity.UserDetail;
import com.userauth.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserRepository userRepository;

    private int calculateAge(Date dob) {
        if (dob == null) return 0;
        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public UserDetail createUserDetail(Long userId, UserDetail userDetail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userDetail.setUser(user);

        // Set age based on DOB
        userDetail.setAge(calculateAge(userDetail.getDOB()));

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
        detail.setAge(calculateAge(updatedDetail.getDOB()));
        detail.setAddress(updatedDetail.getAddress());
        detail.setCountry(updatedDetail.getCountry());
        detail.setPanNumber(updatedDetail.getPanNumber());
        detail.setAadhaarNumber(updatedDetail.getAadhaarNumber());
        detail.setIsActive(updatedDetail.getIsActive());
        detail.setAlternateEmail(updatedDetail.getAlternateEmail());
        detail.setAlternateMobileNumber(updatedDetail.getAlternateMobileNumber());
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
