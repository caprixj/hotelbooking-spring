package com.ips21.hotelbooking.service;

import com.ips21.hotelbooking.model.telegram.Feedback;
import com.ips21.hotelbooking.model.telegram.auth.TelegramBotAuthRequest;
import com.ips21.hotelbooking.model.telegram.auth.TelegramBotAuthResponse;
import com.ips21.hotelbooking.model.telegram.save_feedback.TelegramBotSaveFeedbackRequest;
import com.ips21.hotelbooking.model.telegram.save_feedback.TelegramBotSaveFeedbackResponse;
import com.ips21.hotelbooking.model.user.UserEntity;
import com.ips21.hotelbooking.repository.FeedbackRepository;
import com.ips21.hotelbooking.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ips21.hotelbooking.constants.Constants.TelegramBotErrorMessages.FEEDBACK_NOT_FOUND;
import static com.ips21.hotelbooking.constants.Constants.TelegramBotErrorMessages.INVALID_TELEGRAM_TOKEN;

@Service
@RequiredArgsConstructor
public class TelegramBotService {

    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;

    @Nullable
    public TelegramBotAuthResponse validateTelegramToken(TelegramBotAuthRequest request) {
        String token = request.getTelegramToken();

        UserEntity user = userRepository.findByTelegramToken(token)
            .orElse(null);

        return TelegramBotAuthResponse.builder()
            .success(user != null)
            .build();
    }

    @Nullable
    public TelegramBotSaveFeedbackResponse saveFeedback(TelegramBotSaveFeedbackRequest request) {
        String token = request.getTelegramToken();

        UserEntity user = userRepository.findByTelegramToken(token)
            .orElse(null);

        if (user == null) {
            return TelegramBotSaveFeedbackResponse.builder()
                .saved(false)
                .message(INVALID_TELEGRAM_TOKEN)
                .build();
        }

        String telegramUsername = request.getTelegramUsername();
        String feedbackText = request.getFeedbackText();

        Feedback feedback = Feedback.builder()
            .feedbackText(feedbackText)
            .telegramUsername(telegramUsername)
            .user(user)
            .build();

        feedbackRepository.save(feedback);

        Feedback feedbackWithId = feedbackRepository.findByTelegramUsername(telegramUsername)
                .orElseThrow(() -> new RuntimeException(
                    new Error(FEEDBACK_NOT_FOUND)
                ));

        user.addFeedback(feedbackWithId);
        userRepository.save(user);

        return TelegramBotSaveFeedbackResponse.builder()
            .saved(true)
            .build();
    }
}
