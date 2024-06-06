package com.ips21.hotelbooking.model.auth;

import com.ips21.hotelbooking.exceptions.InvalidEmailException;
import com.ips21.hotelbooking.exceptions.InvalidPasswordException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ips21.hotelbooking.constants.Constants.AuthErrorMessages.INVALID_EMAIL;
import static com.ips21.hotelbooking.constants.Constants.AuthErrorMessages.INVALID_PASSWORD;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String email;
    private String password;

    public void validateCredentials() {
        if (email == null || email.isEmpty())
            throw new InvalidEmailException(INVALID_EMAIL);

        if (password == null || password.isEmpty())
            throw new InvalidPasswordException(INVALID_PASSWORD);
    }
}
