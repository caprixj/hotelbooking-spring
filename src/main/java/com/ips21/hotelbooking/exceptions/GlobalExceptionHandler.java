package com.ips21.hotelbooking.exceptions;

import com.ips21.hotelbooking.model.auth.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleSingUpUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(
            new AuthResponse("User already exists.", null),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleSingUpUserNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(
            new AuthResponse("User does not exist.", null),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(
            new AuthResponse("Bad credentials (possibly invalid password)", null),
            HttpStatus.UNAUTHORIZED
        );
    }

}
