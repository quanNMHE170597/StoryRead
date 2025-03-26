package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Follower")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(FollowerId.class)
public class Follower {

    @Id
    @Column(name = "FollowerId")
    private Long followerId;

    @Id
    @Column(name = "FollowingId")
    private Long followingId;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
