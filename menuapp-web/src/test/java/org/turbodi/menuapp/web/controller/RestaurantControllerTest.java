package org.turbodi.menuapp.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.security.CurrentUser;
import org.turbodi.menuapp.web.service.RestaurantService;
import org.turbodi.menuapp.web.service.VoteService;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.turbodi.menuapp.web.service.TestDataFactory.restaurantDto;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ControllerTestConfig.class)
public class RestaurantControllerTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private RestaurantController restaurantController;

    User user = new User();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(restaurantController).build();
        when(restaurantService.findAll()).thenReturn(Arrays.asList(
                restaurantDto("restaurant1"),
                restaurantDto("restaurant2")
        ));
        when(voteService.vote(any(), any())).thenReturn(restaurantDto("restaurant1"));
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
