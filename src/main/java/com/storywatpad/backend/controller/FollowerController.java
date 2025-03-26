package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Follower;
import com.storywatpad.backend.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followers")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerRepository followerRepository;

    @GetMapping
    public List<Follower> getAll() {
        return followerRepository.findAll();
    }

    @PostMapping
    public Follower create(@RequestBody Follower follower) {
        return followerRepository.save(follower);
    }
}
