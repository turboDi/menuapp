package org.turbodi.menuapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.User;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
