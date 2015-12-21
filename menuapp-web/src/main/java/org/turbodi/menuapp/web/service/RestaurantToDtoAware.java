package org.turbodi.menuapp.web.service;

import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.turbodi.menuapp.data.dao.UserDao;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.web.dto.RestaurantDto;

import static org.turbodi.menuapp.web.service.Checkers.nonNull;

/**
 * @author Dmitriy Borisov
 * @created 12/20/2015
 */
public abstract class RestaurantToDtoAware {

    @Autowired
    private UserDao countUserDao;

    protected static Function<Restaurant, RestaurantDto> TO_DTO = r -> new RestaurantDto(r.getId(), r.getName(), r.isDeleted(), r.getVotesCount());

    protected RestaurantDto toDtoCountRefresh(Restaurant r) {
        nonNull(r).setVotesCount(countUserDao.countByVotedFor(r));
        return TO_DTO.apply(r);
    }
}
