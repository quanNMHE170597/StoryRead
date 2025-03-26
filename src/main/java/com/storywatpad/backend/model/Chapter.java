package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Chapter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChapterId")
    private Long chapterId;

    @Column(name = "StoryId", nullable = false)
    private Long storyId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;
}
