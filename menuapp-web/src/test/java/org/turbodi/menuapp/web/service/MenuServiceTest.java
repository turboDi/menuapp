package org.turbodi.menuapp.web.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.turbodi.menuapp.data.dao.DishDao;
import org.turbodi.menuapp.data.dao.MenuDao;
import org.turbodi.menuapp.data.dao.RestaurantDao;
import org.turbodi.menuapp.data.model.Menu;
import org.turbodi.menuapp.web.dto.MenuDto;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
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
public class MenuServiceTest {

    @Mock
    private MenuDao menuDao;

    @Mock
    private RestaurantDao restaurantDao;

    @Mock
    private DishDao dishDao;

    @InjectMocks
    private MenuService menuService;

    private Date date = new Date();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(menuDao.findByRestaurantId(any())).thenReturn(Arrays.asList(
                menu("rest1", date, "1", "2"),
                menu("rest2", date, "3", "4")
        ));
        when(menuDao.save(any(Menu.class))).then(returnsFirstArg());
        when(restaurantDao.findOne(any())).thenReturn(
                restaurant("restaurant")
        );
    }

    @Test
    public void testFindAll() {
        assertArrayEquals(menuService.findAll(null).toArray(), Arrays.asList(
                menuDto(date, "1", "2"),
                menuDto(date, "3", "4")
        ).toArray());
    }

    @Test
    public void testCreate() {
        MenuDto actual = menuService.create(null, menuDto(date, "1st", "2nd"));
        assertTrue(actual.getDate().after(date));
        actual.setDate(date);
        assertEquals(menuDto(date, "1st", "2nd"), actual);
        verify(menuDao, only()).save(any(Menu.class));
    }
}
