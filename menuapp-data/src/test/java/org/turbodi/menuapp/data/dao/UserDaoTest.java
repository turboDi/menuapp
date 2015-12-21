package org.turbodi.menuapp.data.dao;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public class UserDaoTest extends AbstractPersistenceTest<UserDao> {

    @Override
    protected void before() {
        vote(user1, restaurant1);
        vote(user2, restaurant1);
    }

    @Override
    protected void after() {
        user1.setVotedFor(null);
        user2.setVotedFor(null);
        userDao.save(Arrays.asList(user1, user2));
    }

    @Test
    @Transactional
    public void testFindByUsername() {
        assertEquals(user1, tested.findByUsername("user1"));
        assertEquals(user2, tested.findByUsername("user2"));
    }

    @Test
    @Transactional
    public void testCountByRestaurant() {
        assertEquals(2, tested.countByVotedFor(restaurant1));
        assertEquals(0, tested.countByVotedFor(restaurant2));
    }

    private void vote(User user, Restaurant restaurant) {
        user.setVotedFor(restaurant);
        userDao.save(user);
    }
}
