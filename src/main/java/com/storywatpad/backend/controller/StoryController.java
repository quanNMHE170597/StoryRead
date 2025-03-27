package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Story;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.StoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryRepository storyRepository;

    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @GetMapping
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }
    @GetMapping("/hot")
    public List<Story> getHotStories() {
        return storyRepository.findTop5ByIsHiddenFalseOrderByCreatedAtDesc(); // hoặc tùy cách bạn lọc hot story
    }
    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable Long id) {
        return storyRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
