package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "StoryTagMapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(StoryTagMappingId.class)
public class StoryTagMapping {

    @Id
    @Column(name = "StoryId")
    private Long storyId;

    @Id
    @Column(name = "StoryTagId")
    private Long storyTagId;
}
