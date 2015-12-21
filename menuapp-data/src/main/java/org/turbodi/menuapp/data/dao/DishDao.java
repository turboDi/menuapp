package org.turbodi.menuapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turbodi.menuapp.data.model.Dish;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public interface DishDao extends JpaRepository<Dish, Long> {
}
