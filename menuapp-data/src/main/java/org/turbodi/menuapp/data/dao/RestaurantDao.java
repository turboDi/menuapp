package org.turbodi.menuapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.turbodi.menuapp.data.model.Restaurant;

import java.util.List;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public interface RestaurantDao extends JpaRepository<Restaurant, Long> {

    @Query("SELECT new Restaurant(r, count(v.id)) FROM Restaurant r left join r.votes v group by r order by count(v.id) desc")
    List<Restaurant> findAllOrderByVotes();

}
