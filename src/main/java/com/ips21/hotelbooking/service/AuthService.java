package com.ips21.hotelbooking.service;

import com.ips21.hotelbooking.exceptions.UserAlreadyExistsException;
import com.ips21.hotelbooking.model.auth.AuthResponse;
import com.ips21.hotelbooking.model.auth.LoginRequest;
import com.ips21.hotelbooking.model.auth.SignUpRequest;
import com.ips21.hotelbooking.model.user.UserEntity;
import com.ips21.hotelbooking.repository.UserRepository;
import com.ips21.hotelbooking.model.user.UserRole;
import com.ips21.hotelbooking.jwt.JwtService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final JwtService jwtService;

    // @ Beans
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Nullable
    public AuthResponse signup(SignUpRequest request) {
        String email = request.getEmail();
        if (email == null || email.isEmpty())
            return null;

        String password = request.getPassword();
        if (password == null || password.isEmpty())
            return null;

        if (repository.findByEmail(email).isPresent())
            throw new UserAlreadyExistsException("");

        UserEntity user = UserEntity.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .role(UserRole.USER)
            .build();

        repository.save(user);

        return AuthResponse.builder()
            .token(jwtService.generateToken(user))
            .build();
    }

    @Nullable
    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail();
        if (email == null || email.isEmpty())
            return null;

        String password = request.getPassword();
        if (password == null || password.isEmpty())
            return null;

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        UserEntity user = repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(""));

        return AuthResponse.builder()
            .token(jwtService.generateToken(user))
            .build();
    }

}
