package com.piritter.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.piritter.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  @Query("select u from User u where lower(u.username) like lower(concat(:nameToFind,'%'))")
  List<User> findAllUsernamesLike(@Param("nameToFind") String username);
}
