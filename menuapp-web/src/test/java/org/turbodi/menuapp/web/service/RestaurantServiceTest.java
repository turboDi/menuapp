package org.turbodi.menuapp.web.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.turbodi.menuapp.data.dao.RestaurantDao;
import org.turbodi.menuapp.data.dao.VoteDao;
import org.turbodi.menuapp.data.model.Restaurant;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.turbodi.menuapp.web.service.TestDataFactory.*;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public class RestaurantServiceTest {

    @Mock
    private RestaurantDao restaurantDao;

    @Mock
    private VoteDao voteDao;

    @InjectMocks
    private RestaurantService restaurantService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(restaurantDao.save(any(Restaurant.class))).then(returnsFirstArg());
        when(voteDao.countByRestaurant(any())).thenReturn(0);
        when(restaurantDao.findOne(any())).thenReturn(
                restaurant("restaurant")
        );
    }

    @Test
    public void testCreate() {
        assertEquals(restaurantDto("rest"), restaurantService.create(restaurantDto("rest")));
        verify(restaurantDao, only()).save(any(Restaurant.class));
        verify(voteDao, only()).countByRestaurant(any());
    }

    @Test
    public void testUpdate() {
        assertEquals(restaurantDto("rest"), restaurantService.update(null, restaurantDto("rest")));
    }
}
