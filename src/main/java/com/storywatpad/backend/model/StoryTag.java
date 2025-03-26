package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "StoryTag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StoryTagId")
    private Long storyTagId;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;
}
