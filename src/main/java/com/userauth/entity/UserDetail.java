package com.userauth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
//import java.util.List;

import com.userauth.dto.UserDetailRequest;

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

    @Email
    @NotBlank
    @Column(nullable = false, unique = true )
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String mobileNumber;

    @Temporal(TemporalType.DATE)
    private Date DOB;

    private String address;

    @Column(length = 11)
    private String panNumber;

    @Column(length = 12)
    private String aadhaarNumber;

    private String resumePath;

    public UserDetail(){}

    public UserDetail(UserDetailRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.mobileNumber = request.getMobileNumber();
        this.DOB = request.getDOB();
        this.address = request.getAddress();
        this.panNumber = request.getPanNumber();
        this.aadhaarNumber = request.getAadhaarNumber();
        this.resumePath = request.getResumePath();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
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

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

}



