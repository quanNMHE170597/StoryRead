package com.storywatpad.backend.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkId implements Serializable {
    private Long userId;
    private Long storyId;
}
