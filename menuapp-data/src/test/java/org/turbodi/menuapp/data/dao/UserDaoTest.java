package org.turbodi.menuapp.data.dao;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public class UserDaoTest extends AbstractPersistenceTest<UserDao> {

    @Test
    @Transactional
    public void testFindByUsername() {
        assertEquals(user1, tested.findByUsername("user1"));
        assertEquals(user2, tested.findByUsername("user2"));
    }
}
