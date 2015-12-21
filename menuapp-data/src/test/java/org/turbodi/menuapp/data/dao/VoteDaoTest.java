package org.turbodi.menuapp.data.dao;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.data.model.Vote;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitriy Borisov
 * @created 12/19/2015
 */
public class VoteDaoTest extends AbstractPersistenceTest<VoteDao> {

    @Override
    protected void before() {
        vote(user1, restaurant1);
        vote(user2, restaurant1);
    }

    @Override
    protected void after() {
        user1.setVote(null);
        user2.setVote(null);
        userDao.save(Arrays.asList(user1, user2));
    }

    @Test
    @Transactional
    public void testCountByRestaurant() {
        assertEquals(2, tested.countByRestaurant(restaurant1));
        assertEquals(0, tested.countByRestaurant(restaurant2));
    }

    private void vote(User user, Restaurant restaurant) {
        Vote vote = new Vote();
        vote.setRestaurant(restaurant);
        user.setVote(vote);
        userDao.save(user);
    }
}
