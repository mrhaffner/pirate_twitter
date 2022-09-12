package com.piritter.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;
import com.piritter.api.payload.response.TweetResponse;
import com.piritter.api.service.TimelineService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TimelineControllerTest {
    private User bob;
    private List<TweetResponse> tweetResponses;
    private TweetResponse tweetResponseLogged;
    private TweetResponse tweetResponseUnlogged;
    
    @MockBean
    TimelineService timelineService;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        bob = new User("bob", "adfasdfas");
        tweetResponses = new ArrayList<TweetResponse>();
        tweetResponseLogged = new TweetResponse(new Tweet("hi", bob), bob);
        tweetResponseUnlogged = new TweetResponse(new Tweet("ho", bob));
    }

    @AfterAll
    public void tearDown() {
        tweetResponses = new ArrayList<TweetResponse>();
        Mockito.reset(timelineService);
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="bob")
    public void testGetMyTimeline() throws Exception {
        tweetResponses.add(tweetResponseLogged);
        Mockito.when(timelineService.getHomeTimeline("bob"))
               .thenReturn(tweetResponses);

        mockMvc.perform(get("/api/timeline")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", 
                                    is(tweetResponseLogged.getContent())));
    }

    @Test
    public void testGetMyTimelineWhenNotLogged() throws Exception {
        mockMvc.perform(get("/api/timeline")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetPirateTimelineNotLogged() throws Exception {
        tweetResponses.add(tweetResponseUnlogged);
        Mockito.when(timelineService.getPirateTimeline())
               .thenReturn(tweetResponses);

        mockMvc.perform(get("/api/timeline/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", 
                                    is(tweetResponseUnlogged.getContent())));
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="bob")
    public void testGetPirateTimelineLogged() throws Exception {
        tweetResponses.add(tweetResponseLogged);
        Mockito.when(timelineService.getPirateTimeline("bob"))
               .thenReturn(tweetResponses);

        mockMvc.perform(get("/api/timeline/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", 
                                    is(tweetResponseLogged.getContent())));
    }

    @Test
    public void testGetUserTweetsNotLogged() throws Exception {
        tweetResponses.add(tweetResponseUnlogged);
        Mockito.when(timelineService.getUserTimeline("steve"))
               .thenReturn(tweetResponses);

        mockMvc.perform(get("/api/timeline/user/steve")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", 
                                    is(tweetResponseUnlogged.getContent())));
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="bob")
    public void testGetUserTweetsLogged() throws Exception {
        tweetResponses.add(tweetResponseLogged);
        Mockito.when(timelineService.getUserTimeline("steve", "bob"))
               .thenReturn(tweetResponses);

        mockMvc.perform(get("/api/timeline/user/steve")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", 
                                    is(tweetResponseLogged.getContent())));
    }
}
