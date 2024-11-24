package org.BBT.data.entity;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "Bug")
public class BugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    public BugEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BugEntity(String title, String description) {}

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
}

