package org.turbodi.menuapp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.dao.RestaurantDao;
import org.turbodi.menuapp.data.dao.UserDao;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.web.dto.RestaurantDto;

import java.time.LocalTime;

/**
 * @author Dmitriy Borisov
 * @created 12/20/2015
 */
@PreAuthorize("hasRole('USER')")
@Service
public class VoteService extends RestaurantToDtoAware {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    public RestaurantDto vote(Long restaurantId, User user) {
        Restaurant restaurant = restaurantDao.findOne(restaurantId);
        user.setVotedFor(restaurant);
        userDao.save(user);
        return toDtoCountRefresh(restaurant);
    }

    public boolean canVote(User user) {
        return user.getVotedFor() == null || LocalTime.now().isBefore(LocalTime.of(11, 0));
    }
}
