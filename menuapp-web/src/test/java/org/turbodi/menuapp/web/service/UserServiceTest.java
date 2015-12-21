package org.turbodi.menuapp.web.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.turbodi.menuapp.data.dao.UserDao;
import org.turbodi.menuapp.data.model.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.turbodi.menuapp.web.service.TestDataFactory.userDto;

/**
 * @author Dmitriy Borisov
 * @created 12/19/2015
 */
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(userDao.save(any(User.class))).then(returnsFirstArg());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encoded");
    }

    @Test
    public void testCreate() {
        User user = userService.create(userDto("user", "pwd"));
        assertEquals(User.Role.USER, user.getRole());
        assertEquals("user", user.getUsername());
        assertEquals("encoded", user.getPassword());
        verify(userDao, only()).save(any(User.class));
        verify(passwordEncoder, only()).encode("pwd");
    }
}
