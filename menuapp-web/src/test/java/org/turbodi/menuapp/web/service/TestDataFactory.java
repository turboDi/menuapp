package org.turbodi.menuapp.web.service;

import org.turbodi.menuapp.data.model.Dish;
import org.turbodi.menuapp.data.model.Menu;
import org.turbodi.menuapp.data.model.Restaurant;
import org.turbodi.menuapp.web.dto.DishDto;
import org.turbodi.menuapp.web.dto.MenuDto;
import org.turbodi.menuapp.web.dto.RestaurantDto;
import org.turbodi.menuapp.web.dto.UserDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
public class TestDataFactory {

    public static Restaurant restaurant(String restaurantName) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantName);
        return restaurant;
    }

    public static Menu menu(String restaurantName, Date date, String... dishes) {
        Menu menu = new Menu();
        menu.setRestaurant(restaurant(restaurantName));
        menu.setDate(date);

        for (String dish : dishes) {
            menu.getDishes().add(dish(dish));
        }

        return menu;
    }

    public static Dish dish(String name) {
        Dish dish = new Dish();
        dish.setPrice(BigDecimal.TEN);
        dish.setName(name);
        return dish;
    }

    public static RestaurantDto restaurantDto(String name) {
        return new RestaurantDto(null, name, false, 0);
    }

    public static MenuDto menuDto(Date date, String... dishes) {
        MenuDto menuDto = new MenuDto(null, date, new ArrayList<>(dishes.length));
        for (String dish : dishes) {
            menuDto.getDishes().add(dishDto(dish));
        }
        return menuDto;
    }

    public static DishDto dishDto(String name) {
        return new DishDto(null, name, BigDecimal.TEN);
    }

    public static UserDto userDto(String username, String password) {
        return new UserDto(null, username, password);
    }
}
