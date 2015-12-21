package org.turbodi.menuapp.web.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.dao.RestaurantDao;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.web.dto.RestaurantDto;

import java.util.List;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@Service
@Transactional
public class RestaurantService extends RestaurantToDtoAware {

    @Autowired
    private RestaurantDao restaurantDao;

    @Transactional(readOnly = true)
    public List<RestaurantDto> findAll() {
        return Lists.transform(restaurantDao.findAll(), toDto());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RestaurantDto create(RestaurantDto dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        return toDto().apply(restaurantDao.save(restaurant));
    }

    @Transactional(readOnly = true)
    public RestaurantDto findOne(Long id) {
        return toDto().apply(restaurantDao.findOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RestaurantDto update(Long id, RestaurantDto dto) {
        Restaurant restaurant = restaurantDao.findOne(id);
        restaurant.setName(dto.getName());
        return toDto().apply(restaurantDao.save(restaurant));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        Restaurant restaurant = restaurantDao.findOne(id);
        restaurant.setDeleted(true);
        restaurantDao.save(restaurant);
    }
}
