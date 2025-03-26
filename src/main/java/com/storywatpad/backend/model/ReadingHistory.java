package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ReadingHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ReadingHistoryId.class)
public class ReadingHistory {

    @Id
    @Column(name = "UserId")
    private Long userId;

    @Id
    @Column(name = "StoryId")
    private Long storyId;

    @Id
    @Column(name = "ChapterId")
    private Long chapterId;

    @Column(name = "Like")
    private Integer like;

    @Column(name = "View")
    private Integer view;

    @Column(name = "LastReadAt")
    private LocalDateTime lastReadAt;
}
