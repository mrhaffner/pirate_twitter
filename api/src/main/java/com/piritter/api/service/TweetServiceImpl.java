package com.piritter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;
import com.piritter.api.payload.request.TweetRequest;
import com.piritter.api.payload.response.TweetResponse;
import com.piritter.api.repository.TweetRepository;
import com.piritter.api.repository.UserRepository;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private PirateService pirateService;
    
    @Override
    public TweetResponse saveNewTweet(TweetRequest tweetDto, String currentUserName) throws Exception {
        User user = userRepository
                        .findByUsername(currentUserName)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUserName)));

        String translatedTweet = pirateService.translate(tweetDto.getContent());
        Tweet tweet = new Tweet(translatedTweet, user);
        tweetRepository.save(tweet);
        return new TweetResponse(tweet);
    };

    @Override
    public void deleteUserTweet(String tweetId, String currentUserName) throws Exception {
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(currentUserName)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUserName)));

        if (user.getId() == tweet.getUser().getId()) {
            tweetRepository.deleteById(Long.parseLong(tweetId));
        }
    };

    @Override
    public void likeTweet(String tweetId, String currentUserName) throws Exception {
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(currentUserName)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUserName)));

        if (user.getId() != tweet.getUser().getId()) {
            tweet.getLikedByUserId().add(user.getId());
            tweetRepository.save(tweet);
        }
    };

    @Override
    public void unlikeTweet(String tweetId, String currentUserName) throws Exception {
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(currentUserName)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUserName)));

        if (user.getId() != tweet.getUser().getId()) {
            tweet.getLikedByUserId().remove(user.getId());
            tweetRepository.save(tweet);
        }
    };

    @Override
    public void retweet(String tweetId, String currentUserName) throws Exception {
        Tweet tweet = tweetRepository
                        .findById(Long.parseLong(tweetId))
                        .orElseThrow(() -> new Exception("Tweet not found for tweetId: " + tweetId));  // or return some http status?

        User user = userRepository
                        .findByUsername(currentUserName)
                        .orElseThrow(() -> new Exception(("User not found for username: " + currentUserName)));

        // a user may not retweet their own tweet
        if (user.getId() != tweet.getUser().getId()) {
            // this represents tight coupling, a many to many retweet class could fix this + seperate timeline classes
            tweet.setRetweetCount(tweet.getRetweetCount() + 1);
            tweetRepository.save(tweet);
            user.getRetweets().add(tweet);
            userRepository.save(user);
        }
    };
}
