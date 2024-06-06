package com.ips21.hotelbooking.exceptions;

import com.ips21.hotelbooking.model.auth.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.concurrent.Callable;

import static com.ips21.hotelbooking.constants.Constants.AuthErrorMessages.*;
import static com.ips21.hotelbooking.constants.Constants.BookingErrorMessages.ROOM_BOOKED_TWICE;
import static com.ips21.hotelbooking.constants.Constants.BookingErrorMessages.ROOM_NOT_FREE;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleSingUpUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(
            new AuthResponse(USER_ALREADY_EXISTS, null),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleLoginUserNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(
            new AuthResponse(USER_NOT_FOUND, null),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(
            new AuthResponse(BAD_CREDENTIALS, null),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleInvalidEmailException(InvalidEmailException e) {
        return new ResponseEntity<>(
            new AuthResponse(INVALID_EMAIL, null),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler
    public ResponseEntity<AuthResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        return new ResponseEntity<>(
            new AuthResponse(INVALID_PASSWORD, null),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        String message = e.getMessage();
        boolean isBadRequest = Objects.equals(message, ROOM_BOOKED_TWICE) || Objects.equals(message, ROOM_NOT_FREE);

        return new ResponseEntity<>(
            message,
            isBadRequest
                ? HttpStatus.BAD_REQUEST
                : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
