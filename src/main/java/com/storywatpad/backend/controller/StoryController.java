package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Story;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.StoryRepository;
import com.storywatpad.backend.repository.FollowerRepository;
import com.storywatpad.backend.repository.ReadingHistoryRepository;
import com.storywatpad.backend.repository.ChapterRepository;
import com.storywatpad.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryRepository storyRepository;

    private final ReadingHistoryRepository readingHistoryRepository;
    private final ChapterRepository chapterRepository;
    private final UserRepository userRepository;

    public StoryController(StoryRepository storyRepository,
                           ReadingHistoryRepository readingHistoryRepository,
                           ChapterRepository chapterRepository,
                           UserRepository userRepository) {
        this.storyRepository = storyRepository;
        this.readingHistoryRepository = readingHistoryRepository;
        this.chapterRepository = chapterRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    @GetMapping("/hot")
    public List<Story> getHotStories() {
        return storyRepository.findTop5ByIsHiddenFalseOrderByCreatedAtDesc();
    }

    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
    }

    @GetMapping("/{storyId}/views")
    public int getViewCount(@PathVariable Long storyId) {
        return readingHistoryRepository.countByStoryId(storyId);
    }

    @GetMapping("/{storyId}/likes")
    public int getLikeCount(@PathVariable Long storyId) {
        return readingHistoryRepository.countByStoryIdAndLike(storyId, 1);
    }

    @GetMapping("/{storyId}/chapters/count")
    public int getChapterCount(@PathVariable Long storyId) {
        return chapterRepository.countByStoryId(storyId);
    }

    @GetMapping("/{storyId}/author")
    public User getAuthorByStoryId(@PathVariable Long storyId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));
        return userRepository.findById(story.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
