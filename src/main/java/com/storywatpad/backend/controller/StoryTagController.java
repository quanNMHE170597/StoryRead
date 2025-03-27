package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.StoryTag;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.StoryTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story-tags")
@RequiredArgsConstructor
public class StoryTagController {

    private final StoryTagRepository storyTagRepository;

    @GetMapping
    public List<StoryTag> getAll() {
        return storyTagRepository.findAll();
    }

    @PostMapping
    public StoryTag create(@RequestBody StoryTag tag) {
        return storyTagRepository.save(tag);
    }
    @GetMapping("/{id}")
    public StoryTag getStoryTagById(@PathVariable Long id) {
        return storyTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
