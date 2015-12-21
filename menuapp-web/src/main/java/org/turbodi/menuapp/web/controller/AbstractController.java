package org.turbodi.menuapp.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@Slf4j
public abstract class AbstractController {

    @ExceptionHandler
    public ResponseEntity<String> onError(Exception e) {
        log.error("Unhandled controller error", e);
        return new ResponseEntity<>("Application Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> onAccessDenied() {
        return new ResponseEntity<>("Access is denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<String> onVoteException(VoteException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
