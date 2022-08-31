package com.piritter.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.TweetDto;
import com.piritter.api.model.User;
import com.piritter.api.repository.TweetRepository;
import com.piritter.api.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweet")
public class TweetController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/{username}")
    public List<Tweet> getUserTweets(@PathVariable(value = "username") String username) throws Exception {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        return tweetRepository.findByUser(user);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Tweet createTweet(Principal principal, @RequestBody TweetDto tweetDto) throws Exception { // should be dto/dao?
        String username = principal.getName();
        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        // refactor into service, maybe with the above too
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setContent(tweetDto.getContent());
        tweetRepository.save(tweet);
        return tweet;
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{tweetId}")
    public ResponseEntity<String> deleteTweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        if (user.getId() == tweet.getUser().getId()) { // or .equals?
            tweetRepository.deleteById(Long.parseLong(tweetId));
        }

        // what if not there or not able to delete?
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
