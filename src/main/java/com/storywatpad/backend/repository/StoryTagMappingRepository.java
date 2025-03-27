package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.StoryTagMapping;
import com.storywatpad.backend.model.StoryTagMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryTagMappingRepository extends JpaRepository<StoryTagMapping, StoryTagMappingId> {
    List<StoryTagMapping> findByStoryId(Long storyId);
}
