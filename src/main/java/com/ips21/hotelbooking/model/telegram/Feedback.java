package com.ips21.hotelbooking.model.telegram;

import com.ips21.hotelbooking.model.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue
    private int id;

    private String feedbackText;

    private String telegramUsername;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

}
