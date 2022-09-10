package com.piritter.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piritter.api.payload.response.TweetResponse;
import com.piritter.api.service.TimelineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/timeline")
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<TweetResponse> getMyTimeline(Principal principal) throws Exception {
        String username = principal.getName();
        return timelineService.getHomeTimeline(username);
    }

    // needs pagination
    @GetMapping("/all")
    public List<TweetResponse> getPirateTimeline(Principal principal) throws Exception {
        if (principal == null) {
            return timelineService.getPirateTimeline();
        } else {
            return timelineService.getPirateTimeline(principal.getName());
        }
    }

    @GetMapping("/{username}")
    public List<TweetResponse> getUserTweets(Principal principal, 
                                    @PathVariable(value = "username") String username) throws Exception {
        if (principal == null) {
            return timelineService.getUserTimeline(username);
        } else {
            return timelineService.getUserTimeline(username, principal.getName());
        }
    }
}
