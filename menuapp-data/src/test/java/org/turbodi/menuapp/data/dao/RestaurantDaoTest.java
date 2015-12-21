package org.turbodi.menuapp.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.data.model.Vote;

import java.util.Arrays;

/**
 * @author Dmitriy Borisov
 * @created 12/21/2015
 */
public class RestaurantDaoTest extends AbstractPersistenceTest<RestaurantDao> {

    @Override
    protected void before() {
        vote(user1, restaurant2);
        vote(user2, restaurant2);
    }

    @Override
    protected void after() {
        user1.setVote(null);
        user2.setVote(null);
        userDao.save(Arrays.asList(user1, user2));
    }

    @Test
    @Transactional
    public void testFindAllOrderByVotes() {
        Assert.assertArrayEquals(Arrays.asList(
            restaurant2, restaurant1
        ).toArray(), tested.findAllOrderByVotes().toArray());
    }

    private void vote(User user, Restaurant restaurant) {
        user.setVote(new Vote(restaurant));
        userDao.save(user);
    }
}
