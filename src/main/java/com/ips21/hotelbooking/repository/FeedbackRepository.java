package com.ips21.hotelbooking.repository;

import com.ips21.hotelbooking.model.telegram.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Optional<Feedback> findByTelegramUsername(String telegramUsername);

}
