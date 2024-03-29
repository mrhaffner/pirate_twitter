package com.piritter.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.piritter.api.model.User;
import com.piritter.api.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value="/{followUsername}/follow")
    public void followUser(Principal principal, @PathVariable String followUsername) throws ResponseStatusException {
        String currentUsername = principal.getName();
        try {
            userService.followUser(currentUsername, followUsername);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                                "User not found for username: " + followUsername);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value="/{unfollowUsername}/unfollow")
    public void unfollowUser(Principal principal, @PathVariable String unfollowUsername) throws ResponseStatusException {
        String currentUsername = principal.getName();
        try {
            userService.unfollowUser(currentUsername, unfollowUsername);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                                "User not found for username: " + unfollowUsername);
        }
    }

    @GetMapping(value="/{username}")
    public User getUser(@PathVariable String username) throws ResponseStatusException {
        User user = userService
                        .findByUsername(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                                    "User not found for username: " + username));
        return user;
    }

    @GetMapping(value="/find-similar/{username}")
    public List<User> searchForUsernames(@PathVariable String username) throws Exception {
        return userService.findAllUsernamesLike(username);
    }

    // new, needs tests?
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/token")
    public User getUserFromToken(Principal principal) {
        String currentUsername = principal.getName();
        User user = userService
                .findByUsername(currentUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                            "User not found for username: " + currentUsername));
        return user;
    }
}
