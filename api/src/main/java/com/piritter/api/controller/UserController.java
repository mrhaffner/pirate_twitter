package com.piritter.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public void followUser(Principal principal, @PathVariable String followUsername) throws Exception{
        String currentUsername = principal.getName();
        userService.followUser(currentUsername, followUsername);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value="/{followUsername}/unfollow")
    public void unfollowUser(Principal principal, @PathVariable String unfollowUsername) throws Exception{
        String currentUsername = principal.getName();
        userService.unfollowUser(currentUsername, unfollowUsername);
    }

    // for testing?
    @GetMapping(value="/{username}")
    public User getUser(@PathVariable String username) throws Exception{
        User user = userService
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        return user;
    }

    @GetMapping(value="/search")
    public List<User> searchForUsernames(@RequestParam String username) throws Exception{
        return userService.findAllUsernamesLike(username);
    }
}
