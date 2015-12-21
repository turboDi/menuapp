package org.turbodi.menuapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.data.model.Vote;

/**
 * @author Dmitriy Borisov
 * @created 12/19/2015
 */
public interface VoteDao extends JpaRepository<Vote, Long> {

    int countByRestaurant(Restaurant restaurant);
}
