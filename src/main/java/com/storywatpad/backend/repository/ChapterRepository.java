package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByStoryId(Long storyId);
    int countByStoryId(Long storyId);
    List<Chapter> findByStoryIdOrderByChapterIdAsc(Long storyId);

    Optional<Chapter> findFirstByStoryIdOrderByCreatedAtAsc(Long storyId);
    @Query("SELECT c FROM Chapter c WHERE c.storyId = :storyId AND c.chapterId > :chapterId ORDER BY c.chapterId ASC")
    Optional<Chapter> findNextChapter(@Param("storyId") Long storyId, @Param("chapterId") Long chapterId);
    @Query("SELECT c FROM Chapter c WHERE c.storyId = :storyId AND c.chapterId < :chapterId ORDER BY c.chapterId DESC")
    Optional<Chapter> findPreviousChapter(@Param("storyId") Long storyId, @Param("chapterId") Long chapterId);


}
