package com.ips21.hotelbooking.repository;

import com.ips21.hotelbooking.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findAllByOrderByNumberAsc();
    Optional<Room> findRoomByNumber(int number);

}
