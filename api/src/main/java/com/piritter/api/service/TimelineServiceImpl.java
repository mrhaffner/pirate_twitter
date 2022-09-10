package com.piritter.api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;
import com.piritter.api.payload.response.TweetResponse;
import com.piritter.api.repository.TweetRepository;
import com.piritter.api.repository.UserRepository;

@Service
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TweetResponse> getHomeTimeline(String username) throws Exception {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception(("User not found for username: " + username)));

        Set<Tweet> tweets = new HashSet<>();
        
        for (Long userId : user.getFollowing()) {
            User followingUser = userRepository
                .findById(userId)
                .orElse(null);
            if (followingUser != null) {
                List<Tweet> userTweets = tweetRepository.findByUser(followingUser);
                tweets.addAll(userTweets);
                for (Tweet retweet : followingUser.getRetweets()) {
                    if (retweet.getUser().getId() != user.getId()) {
                        tweets.add(retweet);
                    }
                }
            }
        } 

        return tweetsToTweetResponses(new ArrayList<Tweet>(tweets), user);
    };

    @Override
    public List<TweetResponse> getPirateTimeline() {
        List<Tweet> tweets = tweetRepository.findAll();
        return tweetsToTweetResponses(tweets);
    };

    @Override
    public List<TweetResponse> getPirateTimeline(String username) throws Exception {
        List<Tweet> tweets = tweetRepository.findAll();
        User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        return tweetsToTweetResponses(tweets, user);
    };

    @Override
    public List<TweetResponse> getUserTimeline(String username) throws Exception {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        List<Tweet> tweets = tweetRepository.findByUser(user);
        tweets.addAll(user.getRetweets()); // add retweets

        return tweetsToTweetResponses(tweets);
    };
    
    @Override
    public List<TweetResponse> getUserTimeline(String username, String loggedUsername) throws Exception {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception(("User not found for username: " + username)));
        List<Tweet> tweets = tweetRepository.findByUser(user);
        tweets.addAll(user.getRetweets()); // add retweets

        User loggedUser = userRepository
                        .findByUsername(loggedUsername)
                        .orElseThrow(() -> new Exception(("User not found for username: " + loggedUsername)));
        return tweetsToTweetResponses(tweets, loggedUser);
    };

    private List<TweetResponse> tweetsToTweetResponses(List<Tweet> tweets) {
        return tweets.stream()
                     .map(tweet -> new TweetResponse(tweet))
                     .sorted(Comparator
                                .comparing(tweet -> tweet.getCreationTime(), // sort by id is faster
                                                    Comparator.reverseOrder()))
                     .toList();
    }

    private List<TweetResponse> tweetsToTweetResponses(List<Tweet> tweets, User user) {
        return tweets.stream()
                     .map(tweet -> new TweetResponse(tweet, user))
                     .sorted(Comparator
                                .comparing(tweet -> tweet.getCreationTime(),
                                                    Comparator.reverseOrder()))
                     .toList();
    }
}
