package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.StoryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryTagRepository extends JpaRepository<StoryTag, Long> {
}
