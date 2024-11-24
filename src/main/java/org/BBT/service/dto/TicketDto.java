package org.BBT.service.dto;

import org.BBT.data.entity.BugEntity;
import org.BBT.data.entity.UserEntity;
import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class TicketDto {
    private Long id;
    private BugEntity bug;
    private UserEntity assigneeUser;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TicketDto() {
    }

    public TicketDto(Long id, BugEntity bug, UserEntity assigneeUser, Priority priority, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bug = bug;
        this.assigneeUser = assigneeUser;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BugEntity getBug() {
        return bug;
    }

    public void setBug(BugEntity bug) {
        this.bug = bug;
    }

    public UserEntity getAssigneeUser() {
        return assigneeUser;
    }

    public void setAssigneeUser(UserEntity assigneeUser) {
        this.assigneeUser = assigneeUser;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Objects.equals(id, ticketDto.id) && Objects.equals(bug, ticketDto.bug) && Objects.equals(assigneeUser, ticketDto.assigneeUser) && priority == ticketDto.priority && status == ticketDto.status && Objects.equals(createdAt, ticketDto.createdAt) && Objects.equals(updatedAt, ticketDto.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bug, assigneeUser, priority, status, createdAt, updatedAt);
    }
}
