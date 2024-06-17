package com.ips21.hotelbooking.model.telegram.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramBotAuthResponse {

    Boolean success;
    String message;

}
