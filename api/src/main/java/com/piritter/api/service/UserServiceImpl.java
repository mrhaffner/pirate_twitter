package com.piritter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piritter.api.model.User;
import com.piritter.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void followUser(String currentUsername, String otherUsername) throws Exception {
        User currentUser = userRepository
                        .findByUsername(currentUsername)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUsername)));

        User followUser = userRepository
                .findByUsername(otherUsername)
                .orElseThrow(() -> new Exception(("User not found for username: " + otherUsername)));

        Long currentUserId = currentUser.getId();
        Long followUserId = followUser.getId();

        if (currentUserId != followUserId) {
            currentUser.getFollowing().add(followUserId);
            userRepository.save(currentUser);
        }
    };

    @Override
    public void unfollowUser(String currentUsername, String otherUsername) throws Exception {
        User currentUser = userRepository
                        .findByUsername(currentUsername)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUsername)));

        User followUser = userRepository
                .findByUsername(otherUsername)
                .orElseThrow(() -> new Exception(("User not found for username: " + otherUsername)));

        Long currentUserId = currentUser.getId();
        Long followUserId = followUser.getId();

        if (currentUserId != followUserId) {
            currentUser.getFollowing().remove(followUserId);
            userRepository.save(currentUser);
        }
    };

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    };

    @Override
    public List<User> findAllUsernamesLike(String handle) {
        return userRepository.findAllUsernamesLike(handle);
    }
}
