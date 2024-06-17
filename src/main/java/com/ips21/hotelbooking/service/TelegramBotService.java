package com.ips21.hotelbooking.service;

import com.ips21.hotelbooking.model.auth.AuthResponse;
import com.ips21.hotelbooking.model.telegram_bot.auth_telegram.TelegramBotAuthRequest;
import com.ips21.hotelbooking.model.telegram_bot.auth_telegram.TelegramBotAuthResponse;
import com.ips21.hotelbooking.model.telegram_bot.save_feedback.TelegramBotSaveFeedbackRequest;
import com.ips21.hotelbooking.model.telegram_bot.save_feedback.TelegramBotSaveFeedbackResponse;
import com.ips21.hotelbooking.model.user.UserEntity;
import com.ips21.hotelbooking.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.ips21.hotelbooking.constants.Constants.AuthErrorMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TelegramBotService {

    private final UserRepository userRepository;

    @Nullable
    public TelegramBotAuthResponse validateTelegramToken(TelegramBotAuthRequest request) {
        String token = request.getTelegramToken();

        UserEntity user = userRepository.findByTelegramToken(token)
            .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        return TelegramBotAuthResponse.builder()
            .success(true)
            .build();
    }

    @Nullable
    public TelegramBotSaveFeedbackResponse saveFeedback(TelegramBotSaveFeedbackRequest request) {

    }
}
