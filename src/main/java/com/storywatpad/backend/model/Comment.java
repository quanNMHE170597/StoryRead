package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentId")
    private Long commentId;

    @Column(name = "UserId", nullable = false)
    private Long userId;

    @Column(name = "ChapterId")
    private Long chapterId;

    @Column(name = "StoryId")
    private Long storyId;

    @Column(name = "ParentCommentId")
    private Long parentCommentId;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;
}
