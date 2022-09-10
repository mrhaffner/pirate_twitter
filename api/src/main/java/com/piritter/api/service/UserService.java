package com.piritter.api.service;

import java.util.List;
import java.util.Optional;

import com.piritter.api.model.User;

public interface UserService {
    public void followUser(String currentUsername, String otherUsername) throws Exception;
    public void unfollowUser(String currentUsername, String otherUsername) throws Exception;
    public Optional<User> findByUsername(String username);
    public List<User> findAllUsernamesLike(String username);
}
