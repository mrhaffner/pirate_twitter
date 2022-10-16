package com.piritter.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piritter.api.model.ERole;
import com.piritter.api.model.Role;
import com.piritter.api.model.User;
import com.piritter.api.repository.RoleRepository;
import com.piritter.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

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

    @Override
    public boolean registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }

        User user = new User(username, encoder.encode(password));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }
}
