package com.storywatpad.backend.controller;

import com.storywatpad.backend.model.Comment;
import com.storywatpad.backend.model.CommentResponseDTO;
import com.storywatpad.backend.model.User;
import com.storywatpad.backend.repository.CommentRepository;
import com.storywatpad.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        return commentRepository.findById(id).map(comment -> {
            comment.setContent(updatedComment.getContent());
            comment.setUpdatedAt(updatedComment.getUpdatedAt());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Comment not found"));
    }
    @GetMapping("/story/{storyId}")
    public List<CommentResponseDTO> getCommentsByStoryId(@PathVariable Long storyId) {
        List<Comment> comments = commentRepository.findByStoryId(storyId);
        return comments.stream().map(comment -> {
            User user = userRepository.findById(comment.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return new CommentResponseDTO(
                    comment.getCommentId(),
                    comment.getUserId(),
                    user.getUsername(),
                    comment.getStoryId(),
                    comment.getContent(),
                    comment.getParentCommentId()
            );
        }).collect(Collectors.toList());
    }



}
