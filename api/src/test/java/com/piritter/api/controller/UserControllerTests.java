package com.piritter.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.piritter.api.model.User;
import com.piritter.api.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testSearchUsernames() throws Exception {
        User bob = new User("bob", "adfasdfas");
        List<User> allUsers = Arrays.asList(bob);

        Mockito.when(userService.findAllUsernamesLike("bob")).thenReturn(allUsers);

        mockMvc.perform(get("/api/user/find-similar/bob")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].username", is(bob.getUsername())));
        Mockito.reset(userService);
    }
}
