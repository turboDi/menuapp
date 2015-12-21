package org.turbodi.menuapp.web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.turbodi.menuapp.data.dao.*;
import org.turbodi.menuapp.web.service.MenuService;
import org.turbodi.menuapp.web.service.RestaurantService;
import org.turbodi.menuapp.web.service.UserService;
import org.turbodi.menuapp.web.service.VoteService;

import static org.mockito.Mockito.mock;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@Configuration
@ComponentScan("org.turbodi.menuapp.web.controller")
public class ControllerTestConfig {

    @Bean
    public MenuService menuService() {
        return mock(MenuService.class);
    }

    @Bean
    public RestaurantService restaurantService() {
        return mock(RestaurantService.class);
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public VoteService voteService() {
        return mock(VoteService.class);
    }

    @Bean
    public MenuDao menuDao() {
        return mock(MenuDao.class);
    }

    @Bean
    public DishDao dishDao() {
        return mock(DishDao.class);
    }

    @Bean
    public RestaurantDao restaurantDao() {
        return mock(RestaurantDao.class);
    }

    @Bean
    public UserDao userDao() {
        return mock(UserDao.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return mock(PasswordEncoder.class);
    }

}
