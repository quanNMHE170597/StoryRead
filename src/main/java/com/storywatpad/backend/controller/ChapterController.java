package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Chapter;
import com.storywatpad.backend.model.ReadingHistory;
import com.storywatpad.backend.model.ReadingHistoryId;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.ChapterRepository;
import com.storywatpad.backend.repository.CommentRepository;
import com.storywatpad.backend.repository.ReadingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {
    @Autowired

    private final ChapterRepository chapterRepository;
    @Autowired
    private final ReadingHistoryRepository readingHistoryRepository;
    @Autowired
    private final CommentRepository commentRepository;

    public ChapterController(ChapterRepository chapterRepository, ReadingHistoryRepository readingHistoryRepository, CommentRepository commentRepository) {
        this.chapterRepository = chapterRepository;
        this.readingHistoryRepository = readingHistoryRepository;
        this.commentRepository = commentRepository;
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
    @GetMapping("/previous/{storyId}/{chapterId}")
    public Chapter getPreviousChapter(@PathVariable Long storyId, @PathVariable Long chapterId) {
        return chapterRepository.findPreviousChapter(storyId, chapterId)
                .orElseThrow(() -> new RuntimeException("No previous chapter found"));
    }
    @PutMapping("/{storyId}/{chapterId}/like")
    public ResponseEntity<Void> toggleLike(@PathVariable Long storyId, @PathVariable Long chapterId, @RequestParam Long userId, @RequestParam boolean likeStatus) {
        try {
            Optional<ReadingHistory> readingHistory = readingHistoryRepository.findById(new ReadingHistoryId(userId, storyId, chapterId));

            if (readingHistory.isPresent()) {
                ReadingHistory history = readingHistory.get();
                history.setLike(likeStatus ? 1 : 0);
                readingHistoryRepository.save(history);
            } else {
                // Nếu không có ReadingHistory, tạo mới
                ReadingHistory history = new ReadingHistory(userId, storyId, chapterId, likeStatus ? 1 : 0, 0, LocalDateTime.now());
                readingHistoryRepository.save(history);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log exception để biết chi tiết lỗi
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Trả về lỗi server 500
        }
    }


    @GetMapping("/next/{storyId}/{chapterId}")
    public Chapter getNextChapter(@PathVariable Long storyId, @PathVariable Long chapterId) {
        return chapterRepository.findNextChapter(storyId, chapterId)
                .orElseThrow(() -> new RuntimeException("No next chapter found"));
    }
    @GetMapping("/{storyId}/{chapterId}/views")
    public int getChapterViewCount(@PathVariable Long storyId, @PathVariable Long chapterId) {
        return readingHistoryRepository.getChapterViewCount(storyId, chapterId);
    }

    @GetMapping("/{storyId}/{chapterId}/likes")
    public int getChapterLikeCount(@PathVariable Long storyId, @PathVariable Long chapterId) {
        return readingHistoryRepository.getChapterLikeCount(storyId, chapterId);
    }
    @GetMapping("/{storyId}/{chapterId}/liked")
    public boolean isChapterLiked(@PathVariable Long storyId, @PathVariable Long chapterId, @RequestParam Long userId) {
        Integer likeStatus = readingHistoryRepository.isChapterLiked(userId, storyId, chapterId);

        // Nếu không có kết quả, tức là chưa có bản ghi, ta trả về false
        if (likeStatus == null || likeStatus == 0) {
            return false; // Giả sử nếu không có like thì mặc định là false
        }

        return likeStatus == 1; // Trả về true nếu likeStatus là 1, false nếu là 0
    }




    @GetMapping("/{storyId}/{chapterId}/comments")
    public int getCommentCount(@PathVariable Long storyId, @PathVariable Long chapterId) {
        return commentRepository.getCommentCount(storyId, chapterId);
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
    @GetMapping("/first/{storyId}")
    public Chapter getFirstChapterByStoryId(@PathVariable Long storyId) {
        Optional<Chapter> firstChapter = chapterRepository.findFirstByStoryIdOrderByCreatedAtAsc(storyId);
        return firstChapter.orElseThrow(() -> new RuntimeException("No chapters found for story ID: " + storyId));
    }

}
