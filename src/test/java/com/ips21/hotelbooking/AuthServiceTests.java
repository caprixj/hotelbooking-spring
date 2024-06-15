package com.ips21.hotelbooking;

import static com.ips21.hotelbooking.constants.Constants.AuthErrorMessages.USER_ALREADY_EXISTS;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ips21.hotelbooking.exceptions.UserAlreadyExistsException;
import com.ips21.hotelbooking.jwt.JwtService;
import com.ips21.hotelbooking.model.auth.AuthResponse;
import com.ips21.hotelbooking.model.auth.SignUpRequest;
import com.ips21.hotelbooking.model.user.UserEntity;
import com.ips21.hotelbooking.model.user.UserRole;
import com.ips21.hotelbooking.repository.UserRepository;
import com.ips21.hotelbooking.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void testSignupSuccess() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        String token = "generated-jwt-token";

        SignUpRequest request = mock(SignUpRequest.class);
        when(request.getEmail()).thenReturn(email);
        when(request.getPassword()).thenReturn(password);
        doNothing().when(request).validateCredentials();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        UserEntity user = UserEntity.builder()
            .email(email)
            .password(encodedPassword)
            .role(UserRole.USER)
            .build();

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        // Act
        AuthResponse response = authService.signup(request);

        // Assert
        assertNotNull(response, "AuthResponse should not be null");
        assertEquals(token, response.getToken(), "Token should match the generated token");
    }

    @Test
    void testSignupUserAlreadyExists() {
        // Arrange
        String email = "test1";
        String password = "1234";

        SignUpRequest request = mock(SignUpRequest.class);
        when(request.getEmail()).thenReturn(email);
        when(request.getPassword()).thenReturn(password);
        doNothing().when(request).validateCredentials();

        UserEntity existingUser = new UserEntity();
        existingUser.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        // Act & Assert
        UserAlreadyExistsException exception = assertThrows(
            UserAlreadyExistsException.class,
            () -> authService.signup(request),
            "Expected signup to throw, but it didn't"
        );

        assertEquals(USER_ALREADY_EXISTS, exception.getMessage());
    }
}

