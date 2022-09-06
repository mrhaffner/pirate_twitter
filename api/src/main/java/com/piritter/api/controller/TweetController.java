package com.piritter.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;
import com.piritter.api.payload.request.TweetRequest;
import com.piritter.api.payload.response.TweetResponse;
import com.piritter.api.repository.TweetRepository;
import com.piritter.api.repository.UserRepository;
import com.piritter.api.service.PirateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweet")
public class TweetController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    private PirateService pirateService = new PirateService(); // or AutoWired but make it a Bean?

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public TweetResponse createTweet(Principal principal, @RequestBody TweetRequest tweetDto) throws Exception { // should be dto/dao?
        String username = principal.getName();
        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        String translatedTweet = pirateService.translate(tweetDto.getContent());
        Tweet tweet = new Tweet(translatedTweet, user);
        // Tweet tweet = new Tweet(tweetDto.getContent(), user);
        tweetRepository.save(tweet);
        return new TweetResponse(tweet);
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

    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{tweetId}/like")
    public void likeTweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        if (user.getId() != tweet.getUser().getId()) {
            tweet.getLikedByUserId().add(user.getId());
            tweetRepository.save(tweet);
        }
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{tweetId}/unlike")
    public void unlikeTweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        if (user.getId() != tweet.getUser().getId()) {
            tweet.getLikedByUserId().remove(user.getId());
            tweetRepository.save(tweet);
        }
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{tweetId}/retweet")
    public void retweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        // a user may not retweet their own tweet
        if (user.getId() != tweet.getUser().getId()) {
            // this represents tight coupling, a many to many retweet class could fix this + seperate timeline classes
            tweet.setRetweetCount(tweet.getRetweetCount() + 1);
            tweetRepository.save(tweet);
            user.getRetweets().add(tweet);
            userRepository.save(user);
        }
    }
}
