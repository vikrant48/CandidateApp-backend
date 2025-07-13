package com.userauth.dto;

public class UserAdminView {
    private UserResponse user;
    private UserDetailResponse userDetail;

    public UserAdminView(UserResponse user, UserDetailResponse userDetail) {
        this.user = user;
        this.userDetail = userDetail;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public UserDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }
}
