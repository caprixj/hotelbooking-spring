package com.ips21.hotelbooking.controller;

import com.ips21.hotelbooking.model.telegram_bot.auth_telegram.TelegramBotAuthRequest;
import com.ips21.hotelbooking.model.telegram_bot.auth_telegram.TelegramBotAuthResponse;
import com.ips21.hotelbooking.model.telegram_bot.save_feedback.TelegramBotSaveFeedbackRequest;
import com.ips21.hotelbooking.model.telegram_bot.save_feedback.TelegramBotSaveFeedbackResponse;
import com.ips21.hotelbooking.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TelegramBotController {

    private final TelegramBotService service;

    @PostMapping("/auth_telegram")
    public ResponseEntity<TelegramBotAuthResponse> saveFeedback(
        @RequestBody TelegramBotAuthRequest request
    ) {
        TelegramBotAuthResponse response = service.validateTelegramToken(request);

        return (response != null)
            ? ResponseEntity.ok(response)
            : ResponseEntity.badRequest().build();
    }

    @PostMapping("/save_feedback")
    public ResponseEntity<TelegramBotSaveFeedbackResponse> saveFeedback(
        @RequestBody TelegramBotSaveFeedbackRequest request
    ) {
        TelegramBotSaveFeedbackResponse response = service.saveFeedback(request);

        return (response != null)
            ? ResponseEntity.ok(response)
            : ResponseEntity.badRequest().build();
    }
}
