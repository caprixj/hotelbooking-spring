package com.ips21.hotelbooking.model.telegram_bot.save_feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramBotSaveFeedbackResponse {

    Boolean saved;
    String message;

}
