package com.piritter.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;
import com.piritter.api.repository.TweetRepository;
import com.piritter.api.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/timeline")
public class TimelineController {
    
    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Tweet> getMyTimeline(Principal principal) throws Exception {
        String username = principal.getName();
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        List<Tweet> tweets = new ArrayList<Tweet>();
        
        for (Long userId : user.getFollowing()) {
            User followingUser = userRepository
                .findById(userId)
                .orElse(null);
            if (followingUser != null) {
                List<Tweet> userTweets = tweetRepository.findByUser(followingUser);
                tweets.addAll(userTweets);
            }
        } 

        tweets.sort(Comparator
                .comparing(tweet -> tweet.getCreationTime(), Comparator.reverseOrder()) // could sort by id?
                );        
        return tweets;
    }

    // needs pagination
    @GetMapping("/all")
    public List<Tweet> getPirateTimeline() {
        List<Tweet> tweets = tweetRepository.findAll();
        tweets.sort(Comparator
                        .comparing(tweet -> tweet.getCreationTime(), Comparator.reverseOrder())
                        );        
        return tweets;
    }

    @GetMapping("/{username}")
    public List<Tweet> getUserTweets(@PathVariable(value = "username") String username) throws Exception {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        List<Tweet> tweets = tweetRepository.findByUser(user);
        tweets.sort(Comparator
                .comparing(tweet -> tweet.getCreationTime(), Comparator.reverseOrder())
                );        
        return tweets;
    }
}
