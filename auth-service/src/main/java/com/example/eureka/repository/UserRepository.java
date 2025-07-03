package com.example.eureka.repository;

import com.example.eureka.models.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModels, Long> {

    // Find by email (useful for login/authentication)
    Optional<UserModels> findByEmail(String email);

    // Check if a user exists by email
    boolean existsByEmail(String email);
}

