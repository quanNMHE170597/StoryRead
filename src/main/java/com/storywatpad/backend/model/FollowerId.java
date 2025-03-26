package com.storywatpad.backend.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerId implements Serializable {

    private Long followerId;
    private Long followingId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowerId)) return false;
        FollowerId that = (FollowerId) o;
        return Objects.equals(followerId, that.followerId) &&
                Objects.equals(followingId, that.followingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followingId);
    }
}
