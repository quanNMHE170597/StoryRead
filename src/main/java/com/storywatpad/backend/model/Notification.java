package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationId")
    private Long notificationId;

    @Column(name = "UserId", nullable = false)
    private Long userId;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "ImageLeft", nullable = false)
    private String imageLeft;

    @Column(name = "StoryId")
    private Long storyId;

    @Column(name = "ChapterId")
    private Long chapterId;

    @Column(name = "CommentId")
    private Long commentId;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
