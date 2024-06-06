package com.ips21.hotelbooking.exceptions;

public class InvalidEmailException extends UnsupportedOperationException {

    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }

}