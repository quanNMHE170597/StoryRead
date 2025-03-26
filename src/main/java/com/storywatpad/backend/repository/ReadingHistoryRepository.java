package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.ReadingHistory;
import com.storywatpad.backend.model.ReadingHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingHistoryRepository extends JpaRepository<ReadingHistory, ReadingHistoryId> {
}
