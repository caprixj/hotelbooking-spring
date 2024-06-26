package com.ips21.hotelbooking.model.room;

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
@Table(name = "rooms")
public class Room {

    @Id
    private int number;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private UserEntity owner;

}
