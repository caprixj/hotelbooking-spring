package com.ips21.hotelbooking.controller;

import com.ips21.hotelbooking.model.booking.BookingRequest;
import com.ips21.hotelbooking.service.BookingService;
import com.ips21.hotelbooking.model.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookroom")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        var re = ResponseEntity.ok(service.getAllRooms());
        System.out.println(re);
        return re;
    }

    @PostMapping("/order")
    public ResponseEntity<String> bookRoom(@RequestBody BookingRequest request) {
        try {
            return ResponseEntity.ok(service.bookRoom(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
