package org.turbodi.menuapp.web.service;

/**
 * @author Dmitriy Borisov
 * @created 12/21/2015
 */
public final class Checkers {

    public static <T> T nonNull(T input) {
        if (input != null) {
            return input;
        }
        throw new NotFoundException("Not Found");
    }

    public static class NotFoundException extends RuntimeException {

        public NotFoundException(String message) {
            super(message);
        }
    }

}
