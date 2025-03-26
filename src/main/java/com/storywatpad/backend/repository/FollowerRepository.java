package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.Follower;
import com.storywatpad.backend.model.FollowerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, FollowerId> {
}
