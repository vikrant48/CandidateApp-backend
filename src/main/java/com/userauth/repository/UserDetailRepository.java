package com.userauth.repository;

import com.userauth.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    // Add custom queries if needed
    Boolean existsByEmail(String email);

    Boolean existsByMobileNumber(String mobileNumber);

    Optional<UserDetail> findByEmail(String email);

    Optional<UserDetail> findByMobileNumber(String mobileNumber);
}