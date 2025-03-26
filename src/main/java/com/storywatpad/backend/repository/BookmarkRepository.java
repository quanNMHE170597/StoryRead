package com.storywatpad.backend.repository;

import com.storywatpad.backend.model.Bookmark;
import com.storywatpad.backend.model.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
}
