package com.userauth.dto;

import com.userauth.entity.UserDetail;
import java.util.Date;

public class UserDetailResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Date DOB;
    private String address;
    private String panNumber;
    private String aadhaarNumber;
    private String resumePath;

    public UserDetailResponse(UserDetail detail) {
        this.id = detail.getId();
        this.firstName = detail.getFirstName();
        this.lastName = detail.getLastName();
        this.email = detail.getEmail();
        this.mobileNumber = detail.getMobileNumber();
        this.DOB = detail.getDOB();
        this.address = detail.getAddress();
        this.panNumber = detail.getPanNumber();
        this.aadhaarNumber = detail.getAadhaarNumber();
        this.resumePath = detail.getResumePath();
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
