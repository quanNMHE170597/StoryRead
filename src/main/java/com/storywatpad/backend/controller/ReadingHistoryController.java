package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.ReadingHistory;
import com.storywatpad.backend.model.ReadingHistoryId;
import com.storywatpad.backend.repository.ReadingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reading-history")
@RequiredArgsConstructor
public class ReadingHistoryController {

    private final ReadingHistoryRepository readingHistoryRepository;

    @GetMapping
    public List<ReadingHistory> getAll() {
        return readingHistoryRepository.findAll();
    }

    @PostMapping
    public ReadingHistory create(@RequestBody ReadingHistory readingHistory) {
        return readingHistoryRepository.save(readingHistory);
    }
    // API để cập nhật ReadingHistory (View +1)
    @PutMapping("/update/{userId}/{storyId}/{chapterId}")
    public void updateReadingHistory(@PathVariable Long userId, @PathVariable Long storyId, @PathVariable Long chapterId) {
        ReadingHistory history = readingHistoryRepository.findById(new ReadingHistoryId(userId, storyId, chapterId))
                .orElse(null);

        if (history != null) {
            // Nếu đã có, cập nhật View +1 và thời gian LastReadAt
            history.setView(history.getView() + 1);
            history.setLastReadAt(LocalDateTime.now());
        } else {
            // Nếu chưa có, tạo mới ReadingHistory với View = 1
            history = new ReadingHistory(userId, storyId, chapterId, 0, 1, LocalDateTime.now());
        }

        readingHistoryRepository.save(history);
    }

}
