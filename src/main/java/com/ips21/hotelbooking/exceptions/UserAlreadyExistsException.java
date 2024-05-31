package com.ips21.hotelbooking.exceptions;

public class UserAlreadyExistsException extends UnsupportedOperationException {

    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
