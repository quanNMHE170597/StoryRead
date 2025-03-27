package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.ReadingHistory;
import com.storywatpad.backend.model.ReadingHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingHistoryRepository extends JpaRepository<ReadingHistory, ReadingHistoryId> {
    int countByStoryId(Long storyId);
    int countByStoryIdAndLike(Long storyId, int like);

    @Query("SELECT COALESCE(SUM(r.view), 0) FROM ReadingHistory r WHERE r.storyId = :storyId AND r.chapterId = :chapterId")
    int getChapterViewCount(@Param("storyId") Long storyId, @Param("chapterId") Long chapterId);

    @Query("SELECT COUNT(r) FROM ReadingHistory r WHERE r.storyId = :storyId AND r.chapterId = :chapterId AND r.like = 1")
    int getChapterLikeCount(@Param("storyId") Long storyId, @Param("chapterId") Long chapterId);

    @Query("SELECT r.like FROM ReadingHistory r WHERE r.userId = :userId AND r.storyId = :storyId AND r.chapterId = :chapterId")
    Integer isChapterLiked(@Param("userId") Long userId, @Param("storyId") Long storyId, @Param("chapterId") Long chapterId);
    
    @Modifying
    @Query("UPDATE ReadingHistory r SET r.like = :isLiked WHERE r.userId = :userId AND r.storyId = :storyId AND r.chapterId = :chapterId")
    void updateLikeStatus(@Param("storyId") Long storyId, @Param("chapterId") Long chapterId, @Param("userId") Long userId, @Param("isLiked") boolean isLiked);

    ReadingHistory findTopByStoryIdAndUserIdOrderByLastReadAtDesc(Long storyId, Long userId);

    List<ReadingHistory> findByUserId(Long userId);
}
