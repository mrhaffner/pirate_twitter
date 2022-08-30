package com.piritter.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
      List<Tweet> findByUser(User user);
}
