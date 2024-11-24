package org.BBT.data.entity;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "Tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private BugEntity bug;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity assigneeUser;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TicketEntity() {
    }

    public TicketEntity(Long id, BugEntity bug, UserEntity assigneeUser, Priority priority, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bug = bug;
        this.assigneeUser = assigneeUser;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserEntity getAssigneeUser() {
        return assigneeUser;
    }

    public void setAssigneeUser(UserEntity assigneeUser) {
        this.assigneeUser = assigneeUser;
    }

    public BugEntity getBug() {
        return bug;
    }

    public void setBug(BugEntity bug) {
        this.bug = bug;
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
