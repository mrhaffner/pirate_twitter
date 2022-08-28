package com.piritter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piritter.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}