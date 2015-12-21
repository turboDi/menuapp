package org.turbodi.menuapp.web.service;

import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.turbodi.menuapp.data.dao.VoteDao;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.web.dto.RestaurantDto;

/**
 * @author Dmitriy Borisov
 * @created 12/20/2015
 */
public abstract class RestaurantToDtoAware {

    @Autowired
    private VoteDao voteDao;

    protected Function<Restaurant, RestaurantDto> toDto() {
        return r -> new RestaurantDto(r.getId(), r.getName(), r.isDeleted(), voteDao.countByRestaurant(r));
    }
}
