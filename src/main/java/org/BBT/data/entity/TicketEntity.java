package org.BBT.data.entity;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "bug_id") //@JoinColumn(name = "bug_id", referencedColumnName = "id")
    private BugEntity bug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") //
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(bug, that.bug) && Objects.equals(assigneeUser, that.assigneeUser) && priority == that.priority && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bug, assigneeUser, priority, status, createdAt, updatedAt);
    }
}
