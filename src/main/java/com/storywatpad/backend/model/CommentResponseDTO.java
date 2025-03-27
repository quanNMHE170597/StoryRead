package com.storywatpad.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long userId;
    private String username;
    private Long storyId;
    private String content;
    private Long parentCommentId;
}

