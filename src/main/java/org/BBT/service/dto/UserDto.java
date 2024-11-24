package org.BBT.service.dto;

import org.BBT.data.entity.TicketEntity;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<TicketDto> tickets;

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(tickets, userDto.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, tickets);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
