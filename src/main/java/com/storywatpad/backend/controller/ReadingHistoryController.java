package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Chapter;
import com.storywatpad.backend.model.ReadingHistory;
import com.storywatpad.backend.model.ReadingHistoryId;
import com.storywatpad.backend.model.Story;
import com.storywatpad.backend.repository.ReadingHistoryRepository;
import com.storywatpad.backend.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reading-history")
@RequiredArgsConstructor
public class ReadingHistoryController {

    private final ReadingHistoryRepository readingHistoryRepository;
    private final StoryRepository storyRepository;

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
        ReadingHistory history = readingHistoryRepository.findById(new ReadingHistoryId(userId, storyId, chapterId)).orElse(null);

        if (history != null) {
            // Nếu đã có, cập nhật View +1 và thời gian LastReadAt
            history.setView(history.getView() + 1);
            history.setLastReadAt(LocalDateTime.now());
            readingHistoryRepository.save(history);
        } else {
            // Nếu chưa có, tạo mới ReadingHistory với View = 1
            history = new ReadingHistory(userId, storyId, chapterId, 0, 1, LocalDateTime.now());
            readingHistoryRepository.save(history);
        }
    }
    // API lấy chương gần nhất mà người dùng đã đọc (chapterNow)
    // API để lấy chương người dùng đã đọc gần nhất cho mỗi truyện (chapterNow)
    @GetMapping("/chapterNow/{userId}/{storyId}")
    public Chapter getChapterNow(@PathVariable Long userId, @PathVariable Long storyId,
                                 @RequestParam(required = false) String type) {
        if ("first".equalsIgnoreCase(type)) {
            // Nếu type là 'first', lấy chapter đầu tiên
            ReadingHistory history = readingHistoryRepository.findTopByStoryIdAndUserIdOrderByLastReadAtAsc(storyId, userId);
            if (history != null) {
                return new Chapter(history.getStoryId(), history.getChapterId(), null, null, null, null);
            }
        } else {
            // Mặc định, lấy chapter gần nhất (mới nhất)
            ReadingHistory history = readingHistoryRepository.findTopByStoryIdAndUserIdOrderByLastReadAtDesc(storyId, userId);
            if (history != null) {
                return new Chapter(history.getStoryId(), history.getChapterId(), null, null, null, null);
            }
        }
        return null; // Nếu không tìm thấy chapter nào
    }

    // Lấy danh sách truyện đã đọc của người dùng
    @GetMapping("/user/{userId}")
    public List<Story> getUserHistory(@PathVariable Long userId) {
        // Lấy tất cả ReadingHistory của userId
        List<ReadingHistory> historyList = readingHistoryRepository.findByUserId(userId);

        // Duyệt qua danh sách ReadingHistory và tìm ra chapter mới nhất cho mỗi storyId
        Map<Long, ReadingHistory> latestHistoryMap = new HashMap<>();

        for (ReadingHistory history : historyList) {
            Long storyId = history.getStoryId();
            if (!latestHistoryMap.containsKey(storyId) || latestHistoryMap.get(storyId).getLastReadAt().isBefore(history.getLastReadAt())) {
                latestHistoryMap.put(storyId, history);  // Cập nhật chapter mới nhất cho mỗi storyId
            }
        }

        // Tạo danh sách các story tương ứng với chapter mới nhất
        List<Story> stories = new ArrayList<>();
        for (ReadingHistory latestHistory : latestHistoryMap.values()) {
            Story story = storyRepository.findById(latestHistory.getStoryId()).orElse(null);
            if (story != null) {
                stories.add(story);
            }
        }

        return stories;  // Trả về danh sách các story
    }


}
