package com.piritter.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.piritter.api.model.User;
import com.piritter.api.repository.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value="/{followUsername}/follow")
    public void followUser(Principal principal, @PathVariable String followUsername) throws Exception{
        String currentUsername = principal.getName();
        System.out.println(currentUsername);
        System.out.println(followUsername);
        User currentUser = userRepository
                        .findByUsername(currentUsername)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUsername)));

        User followUser = userRepository
                .findByUsername(followUsername)
                .orElseThrow(() -> new Exception(("User not found for username: " + followUsername)));

        if (!currentUsername.equalsIgnoreCase(followUsername)) {
            currentUser.getFollowing().add(followUser);
            userRepository.save(currentUser);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value="/{followUsername}/unfollow")
    public void unfollowUser(Principal principal, @PathVariable String followUsername) throws Exception{
        String currentUsername = principal.getName();
        System.out.println(currentUsername);
        System.out.println(followUsername);
        User currentUser = userRepository
                        .findByUsername(currentUsername)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUsername)));

        User followUser = userRepository
                .findByUsername(followUsername)
                .orElseThrow(() -> new Exception(("User not found for username: " + followUsername)));

        if (!currentUsername.equalsIgnoreCase(followUsername)) {
            currentUser.getFollowing().remove(followUser);
            userRepository.save(currentUser);
        }
    }

    // for testing?
    @GetMapping(value="/{username}")
    public User getUser(@PathVariable String username) throws Exception{
        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        return user;
    }
}
