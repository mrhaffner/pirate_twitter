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
    private boolean likedByMe;
    private int likeCount;

    public TweetResponse(Tweet tweet) {
        id = tweet.getId();
        creationTime = tweet.getCreationTime();
        content = tweet.getContent();
        user = tweet.getUser();
        likeCount = tweet.getLikedByUserId().size();
        likedByMe = false;
    }

    public TweetResponse(Tweet tweet, User user) {
        this(tweet);
        if (tweet.getLikedByUserId().contains(user.getId())) {
            likedByMe = true;
        }
    }
}
