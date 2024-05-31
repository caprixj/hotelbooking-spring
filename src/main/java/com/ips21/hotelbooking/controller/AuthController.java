package com.ips21.hotelbooking.controller;

import com.ips21.hotelbooking.model.auth.AuthResponse;
import com.ips21.hotelbooking.service.AuthService;
import com.ips21.hotelbooking.model.auth.LoginRequest;
import com.ips21.hotelbooking.model.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request) {
        AuthResponse response = service.signup(request);

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
