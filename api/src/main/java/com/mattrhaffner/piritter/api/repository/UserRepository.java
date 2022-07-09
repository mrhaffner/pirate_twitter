package com.mattrhaffner.piritter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mattrhaffner.piritter.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
