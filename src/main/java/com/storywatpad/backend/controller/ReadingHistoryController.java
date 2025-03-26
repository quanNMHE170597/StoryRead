package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.ReadingHistory;
import com.storywatpad.backend.repository.ReadingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
