package com.userauth.repository;

import com.userauth.entity.UserDetail;
import com.userauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    // Add custom queries if
//    Boolean existsByEmail(String email);
//    Boolean existsByMobileNumber(String mobileNumber);
//
//    Optional<UserDetail> findByEmail(String email);
//    Optional<UserDetail> findByMobileNumber(String mobileNumber);


    List<UserDetail> findByIsActive(boolean isActive);
    Optional<UserDetail> findByUser(User user);

}