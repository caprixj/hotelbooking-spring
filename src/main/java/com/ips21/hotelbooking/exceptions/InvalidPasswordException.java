package com.ips21.hotelbooking.exceptions;

public class InvalidPasswordException extends UnsupportedOperationException {

    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }

}
