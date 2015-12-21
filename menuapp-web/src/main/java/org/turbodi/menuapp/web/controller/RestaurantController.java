package org.turbodi.menuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.turbodi.menuapp.web.dto.RestaurantDto;
import org.turbodi.menuapp.web.service.RestaurantService;

import java.util.List;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantController extends AbstractController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RestaurantDto> getAll() {
        return restaurantService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestaurantDto create(@RequestBody RestaurantDto dto) {
        return restaurantService.create(dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public RestaurantDto get(@PathVariable Long id) {
        return restaurantService.findOne(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public RestaurantDto update(@PathVariable Long id, @RequestBody RestaurantDto dto) {
        return restaurantService.update(id, dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
