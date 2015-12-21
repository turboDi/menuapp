package org.turbodi.menuapp.data.dao;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.model.Dish;
import org.turbodi.menuapp.data.model.Menu;
import org.turbodi.menuapp.data.model.Restaurant;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public class MenuDaoTest extends AbstractPersistenceTest<MenuDao> {

    private Menu menu1, menu2, menu3;

    private Date yesterday;

    @Override
    protected void before() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        yesterday = c.getTime();
        fillMenu(menu1 = new Menu(), restaurant1, yesterday);
        fillMenu(menu2 = new Menu(), restaurant1, new Date());
        fillMenu(menu3 = new Menu(), restaurant2, new Date());
    }

    @Override
    protected void after() {
        tested.deleteAll();
    }

    @Test
    @Transactional
    public void testFindByRestaurantId() {
        List<Menu> restaurant1Menus = tested.findByRestaurantId(restaurant1.getId());
        List<Menu> restaurant2Menus = tested.findByRestaurantId(restaurant2.getId());
        assertEquals(restaurant1Menus.size(), 2);
        assertEquals(restaurant2Menus.size(), 1);
        assertThat(restaurant1Menus, hasItems(menu1, menu2));
        assertThat(restaurant2Menus, hasItems(menu3));
    }

    @Test
    @Transactional
    public void testFindByRestaurantIdAndDate() {
        Menu today1 = tested.findByRestaurantIdAndDate(restaurant1.getId(), new Date());
        Menu yesterday1 = tested.findByRestaurantIdAndDate(restaurant1.getId(), yesterday);
        Menu today2 = tested.findByRestaurantIdAndDate(restaurant2.getId(), new Date());
        assertEquals(menu2, today1);
        assertEquals(menu1, yesterday1);
        assertEquals(menu3, today2);
    }

    private void fillMenu(Menu menu, Restaurant restaurant, Date date) {
        menu.setRestaurant(restaurant);
        menu.setDate(date);

        menu.setDishes(Arrays.asList(createDish("1"), createDish("2"), createDish("2")));
        tested.save(menu);
    }

    private Dish createDish(String name) {
        Dish dish = new Dish();
        dish.setPrice(BigDecimal.TEN);
        dish.setName(name);
        return dish;
    }
}
