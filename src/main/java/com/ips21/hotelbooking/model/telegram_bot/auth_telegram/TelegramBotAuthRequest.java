package com.ips21.hotelbooking.model.telegram_bot.auth_telegram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramBotAuthRequest {

    String telegramToken;

}
