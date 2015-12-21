package org.turbodi.menuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.security.CurrentUser;
import org.turbodi.menuapp.web.dto.RestaurantDto;
import org.turbodi.menuapp.web.service.RestaurantService;
import org.turbodi.menuapp.web.service.VoteService;

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

    @Autowired
    private VoteService voteService;

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

    @RequestMapping(path = "/{id}/votes", method = RequestMethod.POST)
    public RestaurantDto vote(@PathVariable Long id, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User user = currentUser.getUser();
        if (!voteService.canVote(user)) {
            throw new VoteException("It's too late to change your mind");
        }
        return voteService.vote(id, user);
    }

}
