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

import com.piritter.api.payload.request.TweetRequest;
import com.piritter.api.payload.response.TweetResponse;
import com.piritter.api.service.TweetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public TweetResponse createTweet(Principal principal, @RequestBody TweetRequest tweetDto) throws Exception { // should be dto/dao?
        String username = principal.getName();
        return tweetService.saveNewTweet(tweetDto, username);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{tweetId}")
    public ResponseEntity<String> deleteTweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        tweetService.deleteUserTweet(tweetId, username);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{tweetId}/like")
    public void likeTweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        tweetService.likeTweet(tweetId, username);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{tweetId}/unlike")
    public void unlikeTweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        tweetService.unlikeTweet(tweetId, username);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{tweetId}/retweet")
    public void retweet(Principal principal, @PathVariable(value = "tweetId") String tweetId) throws Exception {
        String username = principal.getName();
        tweetService.retweet(tweetId, username);
    }
}
