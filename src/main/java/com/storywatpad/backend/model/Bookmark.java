package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookmark")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(BookmarkId.class)
public class Bookmark {

    @Id
    @Column(name = "UserId")
    private Long userId;

    @Id
    @Column(name = "StoryId")
    private Long storyId;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
