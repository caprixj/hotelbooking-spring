package com.ips21.hotelbooking.auth;

import com.ips21.hotelbooking.admin.Admin;
import com.ips21.hotelbooking.admin.AdminRepository;
import com.ips21.hotelbooking.admin.AdminRole;
import com.ips21.hotelbooking.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository repository;
    private final JwtService jwtService;

    // @ Beans
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        Admin admin = Admin.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(AdminRole.DEFAULT)
            .build();
        repository.save(admin);

        String jwtToken = jwtService.generateToken(admin);

        return AuthResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        Admin admin = repository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        String jwtToken = jwtService.generateToken(admin);

        return AuthResponse.builder()
            .token(jwtToken)
            .build();
    }

}
