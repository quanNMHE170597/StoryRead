package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Notification;
import com.storywatpad.backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }
}
