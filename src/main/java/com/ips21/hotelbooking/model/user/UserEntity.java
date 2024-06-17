package com.ips21.hotelbooking.model.user;

import com.ips21.hotelbooking.model.telegram.Feedback;
import com.ips21.hotelbooking.model.room.Room;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String password;

    private String telegramToken;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "owner")
    private Set<Room> bookedRooms;

    @OneToMany(mappedBy = "user")
    private Set<Feedback> feedbacks;

    public static String generateTelegramToken(String email) throws NoSuchAlgorithmException {
        String token;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(email.getBytes(StandardCharsets.UTF_8));

        StringBuilder base26String = new StringBuilder();
        for (byte b : hash) {
            int value = Byte.toUnsignedInt(b);
            char letter = (char) ((value % 26) + 'A');
            base26String.append(letter);
        }

        token = base26String.substring(0, 6);

        return token;
    }

    public void addBookedRoom(Room newRoom) {
        bookedRooms.add(newRoom);
    }

    public void addFeedback(Feedback newFeedback) {
        feedbacks.add(newFeedback);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
