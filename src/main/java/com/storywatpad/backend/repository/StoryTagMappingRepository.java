package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.StoryTagMapping;
import com.storywatpad.backend.model.StoryTagMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryTagMappingRepository extends JpaRepository<StoryTagMapping, StoryTagMappingId> {
}
