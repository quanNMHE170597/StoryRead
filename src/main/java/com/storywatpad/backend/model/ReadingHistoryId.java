package com.storywatpad.backend.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingHistoryId implements Serializable {

    private Long userId;
    private Long storyId;
    private Long chapterId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadingHistoryId)) return false;
        ReadingHistoryId that = (ReadingHistoryId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(storyId, that.storyId) &&
                Objects.equals(chapterId, that.chapterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, storyId, chapterId);
    }
}
