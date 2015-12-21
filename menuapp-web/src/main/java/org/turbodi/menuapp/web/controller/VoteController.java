package org.turbodi.menuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.turbodi.menuapp.data.model.User;
import org.turbodi.menuapp.security.CurrentUser;
import org.turbodi.menuapp.web.dto.VoteDto;
import org.turbodi.menuapp.web.service.VoteService;

/**
 * @author Dmitriy Borisov
 * @created 12/21/2015
 */
@RestController
@RequestMapping("/restaurants/{restaurantId}/votes")
public class VoteController extends AbstractController {

    @Autowired
    private VoteService voteService;

    private static class VoteException extends RuntimeException {
        public VoteException(String message) {
            super(message);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public VoteDto vote(@PathVariable Long restaurantId, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User user = currentUser.getUser();
        if (!voteService.canVote(user)) {
            throw new VoteException("It's too late to change your mind");
        }
        return voteService.vote(restaurantId, user);
    }

    @ExceptionHandler
    public ResponseEntity<String> onVoteException(VoteException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
