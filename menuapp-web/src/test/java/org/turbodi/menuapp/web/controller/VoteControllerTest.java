package org.turbodi.menuapp.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.security.CurrentUser;
import org.turbodi.menuapp.web.service.VoteService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.turbodi.menuapp.web.service.TestDataFactory.voteDto;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public class VoteControllerTest {

    @Mock
    private VoteService voteService;

    @InjectMocks
    private VoteController voteController;

    User user = new User();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(voteController).build();
        when(voteService.vote(any(), any())).thenReturn(voteDto("restaurant1"));
        user.setUsername("user");
        user.setPassword("pwd");
        user.setRole(User.Role.USER);
    }

    @After
    public void tearDown() {
        Mockito.reset(voteService);
    }

    @Test
    public void testVote() throws Exception {
        when(voteService.canVote(user)).thenReturn(true);
        mockMvc.perform(post("/restaurants/1/votes").with(request -> {
            request.setUserPrincipal(new TestingAuthenticationToken(new CurrentUser(user), null));
            return request;
        })).andExpect(status().isOk());
        verify(voteService, times(1)).vote(any(), any());
    }

    @Test
    public void testRestrictedVote() throws Exception {
        when(voteService.canVote(user)).thenReturn(false);
        mockMvc.perform(post("/restaurants/1/votes").with(request -> {
            request.setUserPrincipal(new TestingAuthenticationToken(new CurrentUser(user), null));
            return request;
        })).andExpect(status().isUnprocessableEntity());
        verify(voteService, times(0)).vote(any(), any());
    }
}
