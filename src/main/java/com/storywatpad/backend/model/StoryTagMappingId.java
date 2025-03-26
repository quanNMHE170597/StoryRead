package com.storywatpad.backend.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryTagMappingId implements Serializable {

    private Long storyId;
    private Long storyTagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoryTagMappingId)) return false;
        StoryTagMappingId that = (StoryTagMappingId) o;
        return Objects.equals(storyId, that.storyId) &&
                Objects.equals(storyTagId, that.storyTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storyId, storyTagId);
    }
}
