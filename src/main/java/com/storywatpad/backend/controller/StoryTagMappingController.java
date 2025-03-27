package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.StoryTagMapping;
import com.storywatpad.backend.repository.StoryTagMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story-tag-mappings")
@RequiredArgsConstructor
public class StoryTagMappingController {

    private final StoryTagMappingRepository repository;

    @GetMapping
    public List<StoryTagMapping> getAll() {
        return repository.findAll();
    }
    @Autowired
    private StoryTagMappingRepository mappingRepo;

    @GetMapping("/story/{storyId}")
    public List<StoryTagMapping> getTagsByStoryId(@PathVariable Long storyId) {
        return mappingRepo.findByStoryId(storyId);
    }

    @PostMapping
    public StoryTagMapping create(@RequestBody StoryTagMapping mapping) {
        return repository.save(mapping);
    }
}
