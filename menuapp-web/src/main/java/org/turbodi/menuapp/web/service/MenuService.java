package org.turbodi.menuapp.web.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.turbodi.menuapp.data.dao.DishDao;
import org.turbodi.menuapp.data.dao.MenuDao;
import org.turbodi.menuapp.data.dao.RestaurantDao;
import org.turbodi.menuapp.data.model.Dish;
import org.turbodi.menuapp.data.model.Menu;
import org.turbodi.menuapp.web.dto.DishDto;
import org.turbodi.menuapp.web.dto.MenuDto;

import java.util.Date;
import java.util.List;

import static org.turbodi.menuapp.web.service.Checkers.nonNull;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@PreAuthorize("hasRole('ADMIN')")
@Service
@Transactional
public class MenuService {

    private static Function<Dish, DishDto> TO_DISH_DTO = d -> new DishDto(d.getId(), d.getName(), d.getPrice());
    private static Function<Menu, MenuDto> TO_DTO = m -> new MenuDto(m.getId(), m.getDate(), Lists.transform(m.getDishes(), TO_DISH_DTO));

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private DishDao dishDao;

    @Transactional(readOnly = true)
    public List<MenuDto> findAll(Long restaurantId) {
        return Lists.transform(menuDao.findByRestaurantId(restaurantId), TO_DTO);
    }

    public MenuDto create(Long restaurantId, MenuDto dto) {
        Menu menu = new Menu();
        menu.setRestaurant(nonNull(restaurantDao.findOne(restaurantId)));
        for (DishDto dish: dto.getDishes()) {
            addDish(menu, dish);
        }
        return TO_DTO.apply(menuDao.save(menu));
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public MenuDto findToday(Long restaurantId) {
        return TO_DTO.apply(nonNull(menuDao.findByRestaurantIdAndDate(restaurantId, new Date())));
    }

    @Transactional(readOnly = true)
    public MenuDto findOne(Long id) {
        return TO_DTO.apply(nonNull(menuDao.findOne(id)));
    }

    public MenuDto update(Long id, MenuDto dto) {
        Menu menu = nonNull(menuDao.findOne(id));
        menu.getDishes().clear();
        for (DishDto dish: dto.getDishes()) {
            addDish(menu, dish);
        }
        return TO_DTO.apply(menuDao.save(menu));
    }

    public void delete(Long id) {
        menuDao.delete(id);
    }

    private void addDish(Menu menu, DishDto dto) {
        Dish dish = dto.getId() != null ? nonNull(dishDao.findOne(dto.getId())) : new Dish();
        dish.setName(dto.getName());
        dish.setPrice(dto.getPrice());
        menu.getDishes().add(dish);
    }
}
