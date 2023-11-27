package com.ips21.hotelbooking.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

    @GetMapping("/admin/demo")
    public ResponseEntity<String> sayAdminDemo() {
        System.out.println("Hello from demo endpoint");
        return ResponseEntity.ok("Hello from ADMIN demo endpoint");
    }

    @GetMapping("/demo")
    public ResponseEntity<String> sayDemo() {
        System.out.println("Hello from demo endpoint");
        return ResponseEntity.ok("Hello from demo endpoint");
    }
}
