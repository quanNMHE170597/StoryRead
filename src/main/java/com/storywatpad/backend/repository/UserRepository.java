package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN Story s ON u.userId = s.authorId WHERE s.storyId = :storyId")
    Optional<User> findByStoryId(@Param("storyId") Long storyId);

}
