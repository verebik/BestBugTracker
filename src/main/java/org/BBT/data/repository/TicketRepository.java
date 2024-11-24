package org.BBT.data.repository;

import org.BBT.data.entity.TicketEntity;
import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findByAssignee(String assignee);
    List<TicketEntity> findByPriority(Priority priority);
    List<TicketEntity> findByStatus(Status status);
    List<TicketEntity> findByPriorityAndStatus(Priority priority, Status status);
    List<TicketEntity> findByCreatedAtBefore(LocalDateTime date);
    List<TicketEntity> findByBug_Id(Long bugId);
}
