package com.ips21.hotelbooking.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LoginRequest extends AuthRequest {

    @Override
    public void validateCredentials() {
        super.validateCredentials();
    }
}
