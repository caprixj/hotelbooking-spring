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
        return ResponseEntity.ok(service.bookRoom(request));
//        String response;
//
//        try {
//            response = service.bookRoom(request);
//            return ResponseEntity.ok(response);
//        } catch (RuntimeErrorException e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
    }

}
