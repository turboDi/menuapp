package org.turbodi.menuapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.turbodi.menuapp.data.dao.UserDao;
import org.turbodi.menuapp.data.model.User;

/**
 * @author Dmitriy Borisov
 * @created 12/19/2015
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user != null) {
            return new CurrentUser(user);
        }
        throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
    }
}
