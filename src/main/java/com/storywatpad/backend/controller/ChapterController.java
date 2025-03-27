package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Chapter;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final ChapterRepository chapterRepository;

    public ChapterController(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @GetMapping
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }
    @GetMapping("/{id}")
    public Chapter getChapterById(@PathVariable Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Chapter createChapter(@RequestBody Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable Long id) {
        chapterRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Chapter updateChapter(@PathVariable Long id, @RequestBody Chapter updatedChapter) {
        return chapterRepository.findById(id).map(chapter -> {
            chapter.setTitle(updatedChapter.getTitle());
            chapter.setContent(updatedChapter.getContent());
            chapter.setUpdatedAt(updatedChapter.getUpdatedAt());
            return chapterRepository.save(chapter);
        }).orElseThrow(() -> new RuntimeException("Chapter not found"));
    }
    @GetMapping("/story/{storyId}")
    public List<Chapter> getChaptersByStoryId(@PathVariable Long storyId) {
        return chapterRepository.findByStoryIdOrderByChapterIdAsc(storyId);
    }

}
