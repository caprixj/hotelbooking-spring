package com.ips21.hotelbooking.repository;

import com.ips21.hotelbooking.model.room.Feedback;
import com.ips21.hotelbooking.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // Optional<Feedback> findByTelegramCode(String telegramCode);

}
