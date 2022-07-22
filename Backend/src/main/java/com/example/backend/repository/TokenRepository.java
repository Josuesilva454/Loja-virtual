package com.example.backend.repository;

import com.example.backend.model.AuthenticationToken;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
 AuthenticationToken findByUser(User user);
}
