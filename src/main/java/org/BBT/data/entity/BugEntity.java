package org.BBT.data.entity;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "bugs")
public class BugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    public BugEntity() {
    }

    public BugEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugEntity bugEntity = (BugEntity) o;
        return Objects.equals(id, bugEntity.id) && Objects.equals(title, bugEntity.title) && Objects.equals(description, bugEntity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}

