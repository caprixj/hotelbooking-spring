package com.ips21.hotelbooking.controller;

import com.ips21.hotelbooking.model.auth.AuthResponse;
import com.ips21.hotelbooking.service.AuthService;
import com.ips21.hotelbooking.model.auth.LoginRequest;
import com.ips21.hotelbooking.model.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()  // REMOVE "/admin" FROM FRONT
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = service.register(request);

        return (response != null)
            ? ResponseEntity.ok(response)
            : ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = service.login(request);

        return (response != null)
            ? ResponseEntity.ok(response)
            : ResponseEntity.badRequest().build();
    }

}
