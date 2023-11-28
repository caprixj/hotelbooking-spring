package com.ips21.hotelbooking.service;

import com.ips21.hotelbooking.model.booking.BookingRequest;
import com.ips21.hotelbooking.model.room.Room;
import com.ips21.hotelbooking.model.user.UserEntity;
import com.ips21.hotelbooking.repository.RoomRepository;
import com.ips21.hotelbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAllByOrderByNumberAsc();
    }

    public String bookRoom(BookingRequest request) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty())
            throw new RuntimeException(new Error("User has not been found in database!"));

        Optional<Room> roomOptional = roomRepository.findRoomByNumber(request.getRoomNumber());
        if (roomOptional.isEmpty())
            throw new RuntimeException(new Error("Room has not been found in database!"));

        UserEntity user = userOptional.get();
        Room bookedRoom = roomOptional.get();

        if (bookedRoom.getOwner() != null) {
            if (bookedRoom.getOwner().getId() == user.getId())
                throw new RuntimeException(new Error("This room is already booked by this user!"));
            else
                throw new RuntimeException(new Error("This room is already booked by another user!"));
        }

        user.addBookedRoom(bookedRoom);
        userRepository.save(user);

        bookedRoom.setOwner(user);
        roomRepository.save(bookedRoom);

        return "The room has been booked successfully.";
    }
}
