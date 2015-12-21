package org.turbodi.menuapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.turbodi.menuapp.data.model.Menu;

import java.util.Date;
import java.util.List;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public interface MenuDao extends JpaRepository<Menu, Long> {

    List<Menu> findByRestaurantId(Long restaurantId);

    Menu findByRestaurantIdAndDate(Long restaurantId, @Temporal Date date);
}
