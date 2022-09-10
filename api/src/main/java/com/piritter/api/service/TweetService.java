package com.piritter.api.service;

import com.piritter.api.payload.request.TweetRequest;
import com.piritter.api.payload.response.TweetResponse;

public interface TweetService {
    public TweetResponse saveNewTweet(TweetRequest tweetDto, String currentUserName) throws Exception;
    public void deleteUserTweet(String tweetId, String currentUserName) throws Exception;
    public void likeTweet(String tweetId, String currentUserName) throws Exception;
    public void unlikeTweet(String tweetId, String currentUserName) throws Exception;
    public void retweet(String tweetId, String currentUserName) throws Exception;
}
