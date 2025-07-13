package com.userauth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;
import com.userauth.dto.UserDetailRequest;
import com.userauth.entity.User;

@Entity
@Table(name = "userDetails")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

//    @Email
//    @NotBlank
//    private String email;
//
//    private String mobileNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Email
    private String alternateEmail;

    private String alternateMobileNumber;

    @Temporal(TemporalType.DATE)
    private Date DOB;

    private Integer age;

    private String bloodGroup;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelation;

    @Size(max = 50)
    private String country;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    private String address;

    @Column(length = 11)
    private String panNumber;

    @Column(length = 12)
    private String aadhaarNumber;

    public UserDetail(){}

    public UserDetail(User user, UserDetailRequest request) {
        this.user = user;
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
//        this.email = request.getEmail();
//        this.mobileNumber = request.getMobileNumber();
        this.isActive = request.getIsActive();
        this.DOB = request.getDOB();
        this.age = request.getAge();
        this.gender = request.getGender();
        this.address = request.getAddress();
        this.panNumber = request.getPanNumber();
        this.aadhaarNumber = request.getAadhaarNumber();
        this.alternateEmail = request.getAlternateEmail();
        this.alternateMobileNumber = request.getAlternateMobileNumber();
        this.bloodGroup = request.getBloodGroup();
        this.emergencyContactName = request.getEmergencyContactName();
        this.emergencyContactNumber = request.getEmergencyContactNumber();
        this.emergencyContactRelation = request.getEmergencyContactRelation();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getMobileNumber() { return mobileNumber; }
//    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }
    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }
    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public Date getDOB() {
        return DOB;
    }
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanNumber() {
        return panNumber;
    }
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }
    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }
    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }
    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }
}



