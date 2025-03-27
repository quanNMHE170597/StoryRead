package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @GetMapping("/story/{storyId}")
    public User getAuthorByStoryId(@PathVariable Long storyId) {
        return userRepository.findByStoryId(storyId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }



    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setAvatarUrl(updatedUser.getAvatarUrl());
            user.setBio(updatedUser.getBio());
            user.setRole(updatedUser.getRole());
            user.setStatus(updatedUser.getStatus());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
