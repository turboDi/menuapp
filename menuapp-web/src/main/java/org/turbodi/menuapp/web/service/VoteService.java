package org.turbodi.menuapp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.dao.RestaurantDao;
import org.turbodi.menuapp.data.dao.UserDao;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.data.model.Vote;
import org.turbodi.menuapp.web.dto.VoteDto;

import java.time.LocalTime;

import static org.turbodi.menuapp.web.service.Checkers.nonNull;

/**
 * @author Dmitriy Borisov
 * @created 12/20/2015
 */
@PreAuthorize("hasRole('USER')")
@Service
public class VoteService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    public VoteDto vote(Long restaurantId, User user) {
        Restaurant restaurant = nonNull(restaurantDao.findOne(restaurantId));
        Vote vote = user.getVote();
        if (vote == null) {
            user.setVote(vote = new Vote());
        }
        vote.setRestaurant(restaurant);
        restaurant.getVotes().add(vote);
        userDao.save(user);
        return new VoteDto(RestaurantService.TO_DTO_COUNT_REFRESH.apply(restaurant));
    }

    public boolean canVote(User user) {
        return user.getVote() == null || LocalTime.now().isBefore(LocalTime.of(11, 0));
    }
}
