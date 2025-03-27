package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findTop5ByIsHiddenFalseOrderByCreatedAtDesc();
}
