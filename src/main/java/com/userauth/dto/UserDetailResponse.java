package com.userauth.dto;

import com.userauth.entity.User;
import com.userauth.entity.UserDetail;

import java.util.Date;

public class UserDetailResponse {
    private Long id;
    private String firstName;
    private String lastName;
//    private String email;
//    private String mobileNumber;
    private Date DOB;
    private Integer age;
    private String address;
    private Boolean isActive;
    private String panNumber;
    private String aadhaarNumber;
    private String alternateEmail;
    private String alternateMobileNumber;
    private String bloodGroup;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelation;
    private String gender;
    private String country;

    private User user;

    public UserDetailResponse(UserDetail detail) {
        this.user = detail.getUser();

        this.id = detail.getId();
        this.firstName = detail.getFirstName();
        this.lastName = detail.getLastName();
//        this.email = detail.getEmail();
//        this.mobileNumber = detail.getMobileNumber();
        this.DOB = detail.getDOB();
        this.age = detail.getAge();
        this.address = detail.getAddress();
        this.isActive = detail.getIsActive();
        this.panNumber = detail.getPanNumber();
        this.aadhaarNumber = detail.getAadhaarNumber();
        this.alternateEmail = detail.getAlternateEmail();
        this.alternateMobileNumber = detail.getAlternateMobileNumber();
        this.bloodGroup = detail.getBloodGroup();
        this.emergencyContactName = detail.getEmergencyContactName();
        this.emergencyContactNumber = detail.getEmergencyContactNumber();
        this.emergencyContactRelation = detail.getEmergencyContactRelation();
        this.gender = detail.getGender() != null ? detail.getGender().name() : null;
        this.country = detail.getCountry();
    }

    public String getEmail() {
        return user != null ? user.getEmail() : null;
    }

    public String getMobileNumber() {
        return user != null ? user.getMobileNumber() : null;
    }

    // Getters and Setters (you can use Lombok to reduce boilerplate)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getMobileNumber() { return mobileNumber; }
//    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public Date getDOB() { return DOB; }
    public void setDOB(Date DOB) { this.DOB = DOB; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getAlternateEmail() { return alternateEmail; }
    public void setAlternateEmail(String alternateEmail) { this.alternateEmail = alternateEmail; }

    public String getAlternateMobileNumber() { return alternateMobileNumber; }
    public void setAlternateMobileNumber(String alternateMobileNumber) { this.alternateMobileNumber = alternateMobileNumber; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactNumber() { return emergencyContactNumber; }
    public void setEmergencyContactNumber(String emergencyContactNumber) { this.emergencyContactNumber = emergencyContactNumber; }

    public String getEmergencyContactRelation() { return emergencyContactRelation; }
    public void setEmergencyContactRelation(String emergencyContactRelation) { this.emergencyContactRelation = emergencyContactRelation; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
