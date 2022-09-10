package com.piritter.api.service;

import java.util.List;

import com.piritter.api.payload.response.TweetResponse;

public interface TimelineService {
    public List<TweetResponse> getHomeTimeline(String username) throws Exception;
    public List<TweetResponse> getPirateTimeline();
    public List<TweetResponse> getPirateTimeline(String username) throws Exception;
    public List<TweetResponse> getUserTimeline(String username) throws Exception;
    public List<TweetResponse> getUserTimeline(String username, String loggedUsername) throws Exception;
}
