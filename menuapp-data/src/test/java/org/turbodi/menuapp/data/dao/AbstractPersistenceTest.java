package org.turbodi.menuapp.data.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.turbodi.menuapp.data.PersistenceTestConfig;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestConfig.class)
public abstract class AbstractPersistenceTest<T> {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected RestaurantDao restaurantDao;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected T tested;

    protected User user1, user2;
    protected Restaurant restaurant1, restaurant2;

    @Before
    public void setup() {
        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("123");
        user1.setRole(User.Role.ADMIN);
        userDao.save(user1);

        user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("123");
        user2.setRole(User.Role.USER);
        userDao.save(user2);

        restaurant1 = new Restaurant();
        restaurant1.setName("rest1");
        restaurantDao.save(restaurant1);

        restaurant2 = new Restaurant();
        restaurant2.setName("rest2");
        restaurantDao.save(restaurant2);

        before();
    }

    protected void before() {
    }

    @After
    public void tearDown() {
        after();

        userDao.deleteAll();
        restaurantDao.deleteAll();
    }

    protected void after() {
    }
}
