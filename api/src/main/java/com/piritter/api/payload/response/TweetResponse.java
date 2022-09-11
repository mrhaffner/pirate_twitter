package com.piritter.api.payload.response;

import java.util.Date;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;

import lombok.Getter;

@Getter
public class TweetResponse {
    private Long id;
    private Date creationTime;
    private String content;
    private User user; // do I need following?
    private boolean likedByMe = false;
    private boolean retweetedByMe = false;
    private int likeCount;
    private int retweetCount;

    public TweetResponse(Tweet tweet) {
        id = tweet.getId();
        creationTime = tweet.getCreationTime();
        content = tweet.getContent();
        user = tweet.getUser();
        likeCount = tweet.getLikedByUserId().size();
        retweetCount = tweet.getRetweetCount();
    }

    public TweetResponse(Tweet tweet, User user) {
        this(tweet);
        if (tweet.getLikedByUserId().contains(user.getId())) {
            likedByMe = true;
        }
        if (user.getRetweets().contains(tweet)) {
            retweetedByMe = true;
        }
    }
}
