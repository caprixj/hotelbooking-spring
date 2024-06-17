package com.ips21.hotelbooking.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String message;
    private String jwtToken;
    private String telegramToken;

    // !!! CHANGE FRONTEND
    // token -> jwtToken, + telegramToken

}
