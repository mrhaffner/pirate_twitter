package com.piritter.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piritter.api.payload.request.TweetRequest;
import com.piritter.api.service.TweetService;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TweetService tweetService;

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testCreateTweet() throws Exception {
        TweetRequest tweetRequest = new TweetRequest();

        mockMvc.perform(post("/api/tweet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tweetRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testMustBeLoggedInToCreateTweet() throws Exception {
        TweetRequest tweetRequest = new TweetRequest();
        mockMvc.perform(post("/api/tweet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tweetRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testDeleteTweet() throws Exception {

        mockMvc.perform(delete("/api/tweet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testMustBeLoggedInToDeleteTweet() throws Exception {
        mockMvc.perform(delete("/api/tweet/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testLikeTweet() throws Exception {
        mockMvc.perform(put("/api/tweet/1/like")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testMustBeLoggedInToLikeTweet() throws Exception {
        mockMvc.perform(put("/api/tweet/1/like")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testUnlikeTweet() throws Exception {
        mockMvc.perform(put("/api/tweet/1/unlike")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testMustBeLoggedInToUnlikeTweet() throws Exception {
        mockMvc.perform(put("/api/tweet/1/unlike")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testRetweet() throws Exception {
        mockMvc.perform(put("/api/tweet/1/retweet")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testMustBeLoggedInToRetweet() throws Exception {
        mockMvc.perform(put("/api/tweet/1/retweet")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
