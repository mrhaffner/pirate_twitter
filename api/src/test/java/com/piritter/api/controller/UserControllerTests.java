package com.piritter.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.piritter.api.model.User;
import com.piritter.api.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    User bob;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public UserControllerTests() {
        bob = new User("bob", "adfasdfas");
    }

    @Test
    public void testSearchUsernames() throws Exception {
        List<User> allUsers = Arrays.asList(bob);

        Mockito.when(userService.findAllUsernamesLike("bob"))
               .thenReturn(allUsers);

        mockMvc.perform(get("/api/user/find-similar/bob")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].username", is(bob.getUsername())));
        Mockito.reset(userService);
    }

    @Test
    public void testGetValidUsername() throws Exception {
        Mockito.when(userService.findByUsername("bob"))
              .thenReturn(Optional.of(bob));

        mockMvc.perform(get("/api/user/bob")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is(bob.getUsername())));
        Mockito.reset(userService);
    }

    @Test
    public void testGetInvalidUsername() throws Exception {
        Mockito.when(userService.findByUsername("steve"))
              .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/user/steve")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
        Mockito.reset(userService);
    }

    @Test
    public void testCannotUnfollowIfNotLoggedIn() throws Exception {
        mockMvc.perform(put("/api/user/bob/unfollow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testUnfollowValidUser() throws Exception {
        Mockito.doNothing().when(userService).unfollowUser("steve", "bob");
        mockMvc.perform(put("/api/user/bob/unfollow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testUnfollowInvalidUser() throws Exception {
        Mockito.doThrow(new Exception()).when(userService).unfollowUser("steve", "bob");
        mockMvc.perform(put("/api/user/bob/unfollow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }

    @Test
    public void testCannotFollowIfNotLoggedIn() throws Exception {
        mockMvc.perform(put("/api/user/bob/follow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testFollowValidUser() throws Exception {
        Mockito.doNothing().when(userService).unfollowUser("steve", "bob");
        mockMvc.perform(put("/api/user/bob/follow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(authorities="ROLE_USER", username="steve")
    public void testFollowInvalidUser() throws Exception {
        Mockito.doThrow(new Exception()).when(userService).followUser("steve", "bob");
        mockMvc.perform(put("/api/user/bob/follow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }
}
