package org.BBT.data.repository;

import org.BBT.data.entity.BugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BugRepository extends JpaRepository<BugEntity, Long> {
    Optional<BugEntity> findById(Long id);
    List<BugEntity> findByDescriptionContaining(String description);
    List<BugEntity> findByDescriptionContainingIgnoreCase(String description);
    List<BugEntity> findByTitleContaining(String title);
}
