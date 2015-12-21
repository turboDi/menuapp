package org.turbodi.menuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.turbodi.menuapp.web.dto.MenuDto;
import org.turbodi.menuapp.web.service.MenuService;

import java.util.List;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
public class MenuController extends AbstractController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MenuDto> getAll(@PathVariable Long restaurantId) {
        return menuService.findAll(restaurantId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public MenuDto create(@PathVariable Long restaurantId, @RequestBody MenuDto dto) {
        return menuService.create(restaurantId, dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public MenuDto get(@PathVariable Long id) {
        return menuService.findOne(id);
    }

    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public MenuDto getToday(@PathVariable Long restaurantId) {
        return menuService.findToday(restaurantId);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public MenuDto update(@PathVariable Long id, @RequestBody MenuDto dto) {
        return menuService.update(id, dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
