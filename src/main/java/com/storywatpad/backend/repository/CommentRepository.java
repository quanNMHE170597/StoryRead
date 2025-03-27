package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByStoryId(Long storyId);
    // Tìm tất cả các comment có parentCommentId (dùng cho replies)
    List<Comment> findByParentCommentId(Long parentCommentId);

}
