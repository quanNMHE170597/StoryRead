package com.storywatpad.backend.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Story")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StoryId")
    private Long storyId;

    @Column(name = "AuthorId", nullable = false)
    private Long authorId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "CoverImageUrl")
    private String coverImageUrl;

    @Column(name = "GenreId", nullable = false)
    private Long genreId;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "isHidden")
    private Boolean isHidden;
}
