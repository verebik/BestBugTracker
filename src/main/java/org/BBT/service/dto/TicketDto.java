package org.BBT.service.dto;

import org.BBT.data.entity.UserEntity;
import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class TicketDto {
    private Long id;
    private BugDto bug;
    private UserDto assigneeUser;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean editable = false;

    public TicketDto() {
    }

    public TicketDto(Long id, BugDto bug, UserDto assigneeUser, Priority priority, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bug = bug;
        this.assigneeUser = assigneeUser;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
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

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BugDto getBug() {
        return bug;
    }

    public void setBug(BugDto bug) {
        this.bug = bug;
    }

    public UserDto getAssigneeUser() {
        return assigneeUser;
    }

    public void setAssigneeUser(UserDto assigneeUser) {
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
}
