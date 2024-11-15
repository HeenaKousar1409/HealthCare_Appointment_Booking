package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.SignupRequest;
import com.example.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
 Optional<User> findByEmail(String email);

//User save(SignupRequest signupRequest);
}
