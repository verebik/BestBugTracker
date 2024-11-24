package org.BBT.service.dto;

import java.util.Objects;

public class BugDto {
    private Long id;
    private String title;
    private String description;

    public BugDto() {
    }

    public BugDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugDto bugDto = (BugDto) o;
        return Objects.equals(id, bugDto.id) && Objects.equals(title, bugDto.title) && Objects.equals(description, bugDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
