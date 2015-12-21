package org.turbodi.menuapp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.dao.UserDao;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.web.dto.UserDto;

import javax.annotation.PostConstruct;

/**
 * @author Dmitriy Borisov
 * @created 12/19/2015
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @PostConstruct
    public void bootstrap() {
        if ("true".equals(env.getProperty("menuapp.admin.bootstrap"))) {
            User admin = new User();
            admin.setRole(User.Role.ADMIN);
            admin.setUsername(env.getRequiredProperty("menuapp.admin.username"));
            admin.setPassword(passwordEncoder.encode(env.getRequiredProperty("menuapp.admin.password")));
            userDao.save(admin);
            create(new UserDto(null, "user", "123"));
        }
    }

    public User create(UserDto dto) {
        User user = new User();
        user.setRole(User.Role.USER);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userDao.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        userDao.delete(id);
    }
}
